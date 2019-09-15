package conterouz.profile;

import java.io.File;
import java.io.IOException;

public class ProfilerUtils {
    public static void start() {
        try {
            File.createTempFile("d", "d");
        } catch (IOException e) {

        }
        profileCmd("start");
    }

    public static void startWithThreads() {
        profileCmd("start", "-t");
    }

    public static ProfileSession startProfileSessionWithThreads(boolean showAfterClose) {
        try {
            final long pid = ProcessHandle.current().pid();
            final String flameFilename = File.createTempFile("flame", ".svg").getAbsolutePath();
            profileCmd(pid, "start", "-t");
            return new ProfileSession(pid, flameFilename, showAfterClose);
        } catch (IOException e) {
            throw new ProcessExecutionException(e);
        }
    }

    public static void stop() {
        profileCmd("stop");
    }

    public static void stopFlame(String flameFilename) {
        profileCmd("stop", "-f", flameFilename);
    }

    public static void stopAndShowFlame() throws IOException {
        stopAndShowFlame(File.createTempFile("flame", ".svg").getAbsolutePath());
    }

    public static void stopAndShowFlame(String flameFilename) throws IOException {
        profileCmd("stop", "-f", flameFilename);
        ProcessUtils.start("yandex-browser", flameFilename);
    }

    public static void stopAndShowFlame(long pid, String flameFilename) {
        profileCmd(pid, "stop", "-f", flameFilename);
        try {
            ProcessUtils.start("yandex-browser", flameFilename);
        } catch (IOException e) {
            throw new ProcessExecutionException(e);
        }
    }

    public static void stop(long pid, String flameFilename) {
        profileCmd(pid, "stop", "-f", flameFilename);
    }

    public static void profileCmd(String cmd) {
        runProfile("profiler.sh", cmd, "" + ProcessHandle.current().pid());
    }

    public static void profileCmd(String cmd, String... args) {
        profileCmd(ProcessHandle.current().pid(), cmd, args);
    }

    public static void profileCmd(long pid, String cmd, String... args) {
        String[] totalArgs = new String[args.length + 3];
        totalArgs[0] = "profiler.sh";
        totalArgs[1] = cmd;
        totalArgs[2] = String.valueOf(ProcessHandle.current().pid());
        System.arraycopy(args, 0, totalArgs, 3, args.length);
        runProfile(totalArgs);
    }

    private static void runProfile(String... cmdAndArgs) {
        try {
            ProcessUtils.run(cmdAndArgs).throwIfNotSuccess();
        } catch (IOException | InterruptedException e) {
            throw new ProcessExecutionException("Error while running profiler", e);
        }
    }
}
