package de.hoomit.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import de.hoomit.hystrix.configuration.MyHystrixCommandConfiguration;

public abstract class AbstractHystrixCommand<R> extends HystrixCommand<R> {

    protected AbstractHystrixCommand(final MyHystrixCommandConfiguration configuration) {
        super(createSetter(configuration));
    }

    private static Setter createSetter(final MyHystrixCommandConfiguration configuration) {
        return Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(configuration.getGroupKey()))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter().withCoreSize(configuration.getThreadPoolSize())
                                .withMaxQueueSize(configuration.getMaxQueueSize()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(configuration.getThreadTimeout())
                        .withCircuitBreakerRequestVolumeThreshold(
                                configuration.getCircuitBreakerRequestVolumeThreshold())
                        .withCircuitBreakerSleepWindowInMilliseconds(
                                configuration.getCircuitBreakerSleepWindowInMilliseconds())
                        .withCircuitBreakerErrorThresholdPercentage(
                                configuration.getCircuitBreakerErrorThresholdPercentage()));
    }
}
