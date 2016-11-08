package ch.fhnw.sna.transport.util;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by cansik on 08.11.16.
 */
public class ApiLimitTracker {
    private final int requestPerTimeRestriction = 3;
    private final int timeRestriction = 1;

    private final CircularFifoQueue<LocalDateTime> downloads = new CircularFifoQueue<>(requestPerTimeRestriction);

    public void checkRestriction() {
        int counter = 0;
        LocalDateTime now = LocalDateTime.now();

        // check difference
        for (int i = downloads.size() - 1; i >= 0; i--) {
            long timeDifference = now.toEpochSecond(ZoneOffset.UTC) - downloads.get(i).toEpochSecond(ZoneOffset.UTC);
            if (timeDifference < timeRestriction) {
                counter++;
            }
        }

        // block thread if needed
        if (counter >= requestPerTimeRestriction) {
            try {
                Thread.sleep(timeRestriction * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // add download time to list
        downloads.add(LocalDateTime.now());
    }
}
