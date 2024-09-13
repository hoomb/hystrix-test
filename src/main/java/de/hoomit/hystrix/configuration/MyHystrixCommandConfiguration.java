package de.hoomit.hystrix.configuration;

public class MyHystrixCommandConfiguration {
    private String groupKey;
    private Integer threadTimeout;
    private Integer threadPoolSize;
    private Integer circuitBreakerRequestVolumeThreshold;
    private Integer circuitBreakerSleepWindowInMilliseconds;
    private Integer circuitBreakerErrorThresholdPercentage;
    private Integer maxQueueSize;

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(final String groupKey) {
        this.groupKey = groupKey;
    }

    public Integer getThreadTimeout() {
        return threadTimeout;
    }

    public void setThreadTimeout(final Integer threadTimeout) {
        this.threadTimeout = threadTimeout;
    }

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(final Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public Integer getCircuitBreakerRequestVolumeThreshold() {
        return circuitBreakerRequestVolumeThreshold;
    }

    public void setCircuitBreakerRequestVolumeThreshold(final Integer circuitBreakerRequestVolumeThreshold) {
        this.circuitBreakerRequestVolumeThreshold = circuitBreakerRequestVolumeThreshold;
    }

    public Integer getCircuitBreakerSleepWindowInMilliseconds() {
        return circuitBreakerSleepWindowInMilliseconds;
    }

    public void setCircuitBreakerSleepWindowInMilliseconds(final Integer circuitBreakerSleepWindowInMilliseconds) {
        this.circuitBreakerSleepWindowInMilliseconds = circuitBreakerSleepWindowInMilliseconds;
    }

    public Integer getCircuitBreakerErrorThresholdPercentage() {
        return circuitBreakerErrorThresholdPercentage;
    }

    public void setCircuitBreakerErrorThresholdPercentage(final Integer circuitBreakerErrorThresholdPercentage) {
        this.circuitBreakerErrorThresholdPercentage = circuitBreakerErrorThresholdPercentage;
    }

    public Integer getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(final Integer maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }
}
