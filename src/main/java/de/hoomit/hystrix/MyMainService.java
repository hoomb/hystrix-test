package de.hoomit.hystrix;

import de.hoomit.hystrix.hystrix.MyHystrixFactory;
import de.hoomit.hystrix.hystrix.RestTemplatePostForExternalServiceHystrixWrapper;
import de.hoomit.hystrix.model.MyExternalRequest;
import de.hoomit.hystrix.model.MyExternalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class MyMainService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMainService.class);

    private MyHystrixFactory myHystrixFactory;

    private Optional<MyExternalResponse> getMyExternalResponse(final String id, final String name) {
        final MyExternalRequest myExternalRequest = createSampleRequest(id, name);
        final RestTemplatePostForExternalServiceHystrixWrapper myHystrixWrapper = myHystrixFactory.create(myExternalRequest);

        return Optional.ofNullable(getRecommendationResponseEntity(myHystrixWrapper, id)).map(HttpEntity::getBody);
    }

    private MyExternalRequest createSampleRequest(final String id, final String name) {
        final MyExternalRequest myExternalRequest = new MyExternalRequest();

        myExternalRequest.setId(id);
        myExternalRequest.setName(name);

        return myExternalRequest;
    }

    private ResponseEntity<MyExternalResponse> getRecommendationResponseEntity(final RestTemplatePostForExternalServiceHystrixWrapper recommendationHystrixWrapper, final String id) {
        try {
            return recommendationHystrixWrapper.queue().get();
        } catch (final ExecutionException e) {
            final String message = String.format("Execution failure for retrieving data for id: %s", id);

            LOGGER.error(message, e);
            if (!isHttp4xxClientError(e)) {
                throw new RuntimeException(message);
            }
        } catch (final InterruptedException e) {
            final String message = String.format("Interrupted failure for retrieving data for id: %s", id);

            LOGGER.error(message, e);
            Thread.currentThread().interrupt();
            throw new RuntimeException(message);
        }

        return ResponseEntity.badRequest().build();
    }


    private boolean isHttp4xxClientError(final ExecutionException ex) {
        if (ex.getCause() != null && ex.getCause().getCause() instanceof HttpClientErrorException) {
            final HttpClientErrorException exception = (HttpClientErrorException) ex.getCause().getCause();
            LOGGER.error("External Service API Call failed with status code {} and message: {}", exception.getStatusCode(), exception.getMessage());
            return exception.getStatusCode().is4xxClientError();
        }
        return false;
    }
}
