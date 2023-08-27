package leakyBucket;

import java.time.Instant;

// Interface for the bucket
interface Bucket {
    boolean isEmpty();
    void add(int amount);
}

// Leaky Bucket implementation following Single Responsibility Principle
public class LeakyBucket implements Bucket {
    private int capacity;
    private int currentVolume;
    private long lastLeakTime;
    private double leakRate;
    private final Object lock = new Object();

    public LeakyBucket(int capacity, double leakRate) {
        this.capacity = capacity;
        this.currentVolume = 0;
        this.lastLeakTime = System.currentTimeMillis();
        this.leakRate = leakRate;
        bucketLeaker();
    }

    private void bucketLeaker(){
        Thread waterLeakThread = new Thread(() -> {
            while (true) {
                this.leak();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        waterLeakThread.start();
    }

    private void leak() {
        long currentTime = System.currentTimeMillis();
        double timeElapsed = (currentTime - lastLeakTime) / 1000.0; // Convert to seconds

        int leakedVolume = (int) (timeElapsed * leakRate);
        synchronized(lock){
            currentVolume = Math.max(0, currentVolume - leakedVolume);
            Instant currTime = Instant.now();
            System.out.println(currTime + "\tStarted leaking water. Checking the current volume: " + currentVolume);
        }
        lastLeakTime = currentTime;
    }

    public int getCurVolume(){
        synchronized (lock){
            return currentVolume;
        }
    }

    public void add(int amount) {
        synchronized (lock) {
            if (amount > capacity - currentVolume) {
                currentVolume = capacity;
            } else {
                currentVolume += amount;
            }
        }
    }

    @Override
    public synchronized boolean isEmpty() {
        return currentVolume <= 0;
    }
}

class TestMain {
    public static void main(String[] args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(100, 5.0); // Capacity of 100 units, leak rate of 5 units per second
        Thread.sleep(5000);
        Thread waterAdditionThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Instant currTime = Instant.now();
                bucket.add(20);
                System.out.println(currTime + "\tAdded 20 units of water. Current volume: " + bucket.getCurVolume());
                try {
                    Thread.sleep(1000); // Simulate water addition every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        waterAdditionThread.start();
        waterAdditionThread.join();
    }
}
