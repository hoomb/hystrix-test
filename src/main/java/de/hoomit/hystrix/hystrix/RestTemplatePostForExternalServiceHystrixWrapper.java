package de.hoomit.hystrix.hystrix;

import de.hoomit.hystrix.configuration.MyHystrixCommandConfiguration;
import de.hoomit.hystrix.model.MyExternalRequest;
import de.hoomit.hystrix.model.MyExternalResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTemplatePostForExternalServiceHystrixWrapper extends AbstractHystrixCommand<ResponseEntity<MyExternalResponse>> {

    private final RestTemplate restTemplate;
    private final MyExternalRequest myExternalRequest;

    public RestTemplatePostForExternalServiceHystrixWrapper(final RestTemplate restTemplate,
                                                            final MyHystrixCommandConfiguration configuration,
                                                            final MyExternalRequest myExternalRequest) {
        super(configuration);
        this.restTemplate = restTemplate;
        this.myExternalRequest = myExternalRequest;
    }

    @Override
    protected ResponseEntity<MyExternalResponse> run() throws RestClientException {
        final HttpHeaders headers = new HttpHeaders();

        final HttpEntity<?> requestEntity = new HttpEntity<>(
                myExternalRequest,
                headers
        );

        final String serviceUrl = "https://external-service.com?parameter1=foo&parameter2=bar";

        return restTemplate.exchange(
                serviceUrl,
                HttpMethod.POST,
                requestEntity,
                MyExternalResponse.class
        );
    }
}
