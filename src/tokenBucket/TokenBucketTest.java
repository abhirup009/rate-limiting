package tokenBucket;

import commons.Constants;

class TokenBucketTest {
    public static void main(String args[]) {
        TokenBucket tokenBucket = new TokenBucket(
                Constants.TokenBucket.MAX_BUCKET_SIZE,
                Constants.TokenBucket.TOKEN_FILL_RATE_PER_SECOND
        );

        try {
            // This is present to provide some time for the token bucket to be filled up.
            Thread.sleep(Constants.TokenBucket.INITIAL_DELAY_MS_FOR_TOKEN_BUCKET_FILL);
        } catch (Exception e) {
        }

        Thread requestThread = new AddRequestThread(tokenBucket, Constants.TokenBucket.REQUESTS_PER_SECOND);

        try {
            requestThread.start();
        } catch (Exception e) {
        }
    }
}
