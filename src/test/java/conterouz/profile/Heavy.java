package conterouz.profile;

import conterouz.profile.ProfilerUtils;

import java.io.IOException;

public class Heavy {
    public static void main(String[] args) throws IOException {
        stringConcat(1_00_000);
        ProfilerUtils.startWithThreads();
        final long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            stringConcat(1_000_000);
        }

        final long finish = System.currentTimeMillis();
        System.out.println("Cycle eplased " + (finish - start) + " millis");
        ProfilerUtils.stopAndShowFlame();
    }

    public static void intCycle(int limit) {
        for (int i = 0; i < limit; i++) {

        }
    }

    public static void longCycle(long limit) {
        for (long i = 0; i < limit; i++) {

        }
    }

    public static void stringConcat(long times) {
        String s = "";
        for (int i = 0; i < times; i++) {
            s = "";
        }
    }
}
