package ch.fhnw.sna.transport;

import ch.fhnw.sna.transport.util.ApiLimitTracker;

/**
 * Created by cansik on 08.11.16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Test");

        ApiLimitTracker tracker = new ApiLimitTracker();

        for (int i = 0; i < 20; i++) {
            tracker.checkRestriction();
            System.out.println("download " + i + "...");
        }
    }
}
