如果将@HystrixCommand注解加到方法上，不对属性（如CoreSize）做任何配置，那么相当于使用了如下缺省配置。 每个属性的意义可以参考  hystrix学习资料
@HystrixCommand(
            commandProperties = {
                    //execution
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel", value = "false"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    //fallback
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    @HystrixProperty(name = "fallback.enabled", value = "true"),
                    //circuit breaker
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false"),
                    @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    //Metrics
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "true"),
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
                    @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "6"),
                    @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
                    //request context
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),
                    @HystrixProperty(name = "requestLog.enabled", value = "true")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maximumSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "false"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10")
            }
    )
    如果要自定义这些属性，那么需要先了解下hystrix属性配置的优先级。
