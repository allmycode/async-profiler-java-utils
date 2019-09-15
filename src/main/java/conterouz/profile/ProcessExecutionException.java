package conterouz.profile;

public class ProcessExecutionException extends RuntimeException {
    public final ProcessResult processResult;

    public ProcessExecutionException(Throwable cause) {
        super(cause);
        processResult = null;
    }

    public ProcessExecutionException(String message, Throwable cause) {
        super(message, cause);
        processResult = null;
    }

    public ProcessExecutionException(ProcessResult processResult) {
        super(toMessage(processResult));
        this.processResult = processResult;
    }

    public int exitValue() {
        return processResult != null ? processResult.exitCode : -1;
    }

    private static String toMessage(ProcessResult processResult) {
        StringBuilder sb = new StringBuilder();
        if (processResult.stderr != null && processResult.stderr.length() > 0) {
            sb.append("stderr: ").append(processResult.stderr);
        }
        if (processResult.stdout != null && processResult.stdout.length() > 0) {
            sb.append('\n').append("stdout: ").append(processResult.stdout);
        }
        return sb.toString();
    }



}
