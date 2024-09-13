package de.hoomit.hystrix.hystrix;

import de.hoomit.hystrix.configuration.MyHystrixCommandConfiguration;
import de.hoomit.hystrix.model.MyExternalRequest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MyHystrixFactory {
    private RestTemplate restTemplate;
    private MyHystrixCommandConfiguration myHystrixCommandConfiguration;

    public RestTemplatePostForExternalServiceHystrixWrapper create(final MyExternalRequest myExternalRequest) throws RestClientException {
        return new RestTemplatePostForExternalServiceHystrixWrapper(restTemplate, myHystrixCommandConfiguration, myExternalRequest);
    }
}
