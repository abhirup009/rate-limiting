package slidingWindowCounter;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class SlidigWindowCounter {
    private volatile int currentSlot;
    private final AtomicLong[] requestCounts;
    private final long windowSizeInMilliSeconds;

    public SlidigWindowCounter(long windowSizeInMilliSeconds) {
        int slotCount = (int) (windowSizeInMilliSeconds / 1000);
        this.requestCounts = new AtomicLong[slotCount];
        for (int i = 0; i < slotCount; i++) {
            requestCounts[i] = new AtomicLong(0);
        }
        this.windowSizeInMilliSeconds = windowSizeInMilliSeconds;
    }

    public boolean allowRequest(long currentTime, long maxRequests) {
        int slot = (int) ((currentTime / 1000) % requestCounts.length);
        Instant currTime = Instant.now();
        System.out.println(currTime + "\tSlot: " + slot + "Count:" + requestCounts[slot].get()
                + " Current slot: " + currentSlot + "Count:" + requestCounts[currentSlot].get());
        if (slot != currentSlot) {
            synchronized (this) {
                if (slot != currentSlot) {
                    currentSlot = slot;
                    requestCounts[currentSlot].set(0);
                }
            }
        }
        long totalCount = 0;
        for (AtomicLong requestCount : requestCounts) {
            totalCount += requestCount.get();
        }
        if (totalCount >= maxRequests) {
            return false;
        }

        requestCounts[currentSlot].incrementAndGet();
        return true;
    }
}
