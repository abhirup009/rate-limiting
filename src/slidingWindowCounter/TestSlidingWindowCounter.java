package slidingWindowCounter;

import java.time.Instant;

public class TestSlidingWindowCounter {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 2);

        for(int i=0;i<10;i++) {
            if (rateLimiter.allowRequest("poddar.aryan")) {
                Instant now = Instant.now();
                System.out.println(now + "\tAllowed request " + i);
            } else {
                Instant now = Instant.now();
                System.out.println(now + "\tRequest " + i + " blocked");
            }
            Thread.sleep(200);
        }
    }

}
