package conterouz.profile;

public class ProfileSession implements AutoCloseable {
    private final long pid;
    private final String flameFilename;
    private final boolean showAfterClose;

    public ProfileSession(long pid, String flameFilename) {
        this.pid = pid;
        this.flameFilename = flameFilename;
        this.showAfterClose = false;
    }

    public ProfileSession(long pid, String flameFilename, boolean showAfterClose) {
        this.pid = pid;
        this.flameFilename = flameFilename;
        this.showAfterClose = showAfterClose;
    }

    public static ProfileSession create() {
        return ProfilerUtils.startProfileSessionWithThreads(true);
    }

    public long getPid() {
        return pid;
    }

    public String getFlameFilename() {
        return flameFilename;
    }

    public void close() {
        if (showAfterClose) {
            ProfilerUtils.stopAndShowFlame(pid, flameFilename);
        } else {
            ProfilerUtils.stop(pid, flameFilename);
        }
    }
}
