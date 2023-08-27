package slidingWindowLog;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog {
  int maximumRequestsPerSecond;
  private final Queue<Instant> log = new LinkedList<>();

  protected SlidingWindowLog(int maximumRequestsPerSecond) {
    this.maximumRequestsPerSecond = maximumRequestsPerSecond;
  }

  void isAllowed(String requestId) {
    Instant currentTime = Instant.now();
    Instant previousBoundary = currentTime.minus(1, ChronoUnit.SECONDS);

    synchronized (log) {
      while (!log.isEmpty() && log.element().isBefore(previousBoundary)) {
        log.poll();
      }

      log.add(currentTime);
      boolean isAllowed = log.size() <= maximumRequestsPerSecond;
      System.out.println(currentTime + "\t" + requestId + "\tIsAllowed: " + isAllowed + "\t" + log);
    }
  }
}