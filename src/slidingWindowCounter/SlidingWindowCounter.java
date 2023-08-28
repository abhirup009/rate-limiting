package slidingWindowCounter;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounter {
    private final ConcurrentMap<Instant, AtomicInteger> windows = new ConcurrentHashMap<>();
    int maximumRequestPerSec;

    protected SlidingWindowCounter(int maximumRequestPerSec) {
        this.maximumRequestPerSec = maximumRequestPerSec;
    }

    void isAllowed(String requestId) {
        Instant currentTime = Instant.now();
        Instant currentWindowKey = currentTime.truncatedTo(ChronoUnit.SECONDS);

        windows.putIfAbsent(currentWindowKey, new AtomicInteger(0));

        Instant previousWindowKey = currentWindowKey.minusSeconds(1);

        AtomicInteger previousWindowCount = windows.get(previousWindowKey);

        if (previousWindowCount == null) {
            boolean isAllowed = windows.get(currentWindowKey).incrementAndGet() <= maximumRequestPerSec;
            System.out.println(currentTime + "\t" + requestId + "\tIsAllowed: " + "\t" + isAllowed + "\t" + windows + "\tInternal");
        } else {
            long previousWeight = (1000 - Duration.between(currentTime, currentWindowKey).toMillisPart()) / 1000;

            long currentWindowCount = previousWindowCount.get() * previousWeight + windows.get(currentWindowKey).incrementAndGet();

            boolean isAllowed = currentWindowCount <= maximumRequestPerSec;
            System.out.println(currentTime + "\t" + requestId + "\tIsAllowed: " + "\t" + isAllowed + "\t" + windows + "\tExternal");
        }
    }
}
