package slidingWindowCounter;

public interface RateLimiter {
    public boolean allowRequest(String key);
}
