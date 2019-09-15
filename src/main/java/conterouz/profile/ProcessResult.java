package conterouz.profile;

public class ProcessResult {
    public final int exitCode;
    public final String stdout;
    public final String stderr;

    public ProcessResult(int exitCode, String stdout, String stderr) {
        this.exitCode = exitCode;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public void print() {
        System.out.println("Exit code: " + exitCode);
        if (stdout != null && stdout.length() > 0) {
            System.out.println("stdout: " + stdout);
        }
        if (stderr != null && stderr.length() > 0) {
            System.out.println("stderr: " + stderr);
        }
    }

    public void printIfNotSuccess() {
        if (exitCode != 0) {
            print();
        }
    }

    public void throwIfNotSuccess() {
        if (exitCode != 0) {
            throw new ProcessExecutionException(this);
        }
    }
}
