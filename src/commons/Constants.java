package commons;

public class Constants {
    public static class TokenBucket {
        public static int MAX_BUCKET_SIZE = 10;
        public static int TOKEN_FILL_RATE_PER_SECOND = 2;
        public static int INITIAL_DELAY_MS_FOR_TOKEN_BUCKET_FILL = 10000;
        public static int REQUESTS_PER_SECOND = 10;
    }

    public static class SlidingWindowCounter {
        public static int REQUESTS_PER_SECOND = 10;
        public static int MAXIMUM_ALLOWED_REQUESTS_PER_SECOND = 4;
    }

    public static class SlidingWindowLog {
        public static int REQUESTS_PER_SECOND = 2;
        public static int MAXIMUM_ALLOWED_REQUESTS_PER_SECOND = 4;
    }
}
