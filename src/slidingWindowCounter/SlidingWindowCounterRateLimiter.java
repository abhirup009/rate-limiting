package slidingWindowCounter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.TimeUnit;

public class SlidingWindowCounterRateLimiter implements RateLimiter{
    private final ConcurrentHashMap<String,SlidigWindowCounter> counters ;
    private final long maxRequests;
    private final long windowSize;
    private AtomicLong[] requestLog;

    public SlidingWindowCounterRateLimiter(long maxRequests, long windowSize) {
        this.maxRequests = maxRequests;
        this.windowSize = TimeUnit.SECONDS.toMillis(windowSize);
        this.counters = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String key) {
        System.out.println("Window size in millis : " + windowSize);
        long currentTime = System.currentTimeMillis();
        counters.computeIfAbsent(key, k -> new SlidigWindowCounter(windowSize));
        SlidigWindowCounter slidigWindowCounter = counters.get(key);
        return slidigWindowCounter.allowRequest(currentTime,maxRequests);
    }
}
