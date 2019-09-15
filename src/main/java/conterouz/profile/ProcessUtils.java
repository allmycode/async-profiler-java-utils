package conterouz.profile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class ProcessUtils {

    public static ProcessResult run(String cmd) throws IOException, InterruptedException {
        return run(new String[]{cmd});
    }

    public static ProcessResult run(String... commandAndArgs) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(commandAndArgs);
        Process pwdProcess = pb.start();
        pwdProcess.waitFor();
        final String stdout = readToString(pwdProcess.getInputStream());
        final String stderr = readToString(pwdProcess.getErrorStream());
        return new ProcessResult(pwdProcess.exitValue(), stdout, stderr);
    }

    public static void start(String... commandAndArgs) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(commandAndArgs);
        pb.start();
    }

    private static String readToString(InputStream is) throws IOException {
        char[] buf = new char[1024];
        int readed;
        InputStreamReader isr = new InputStreamReader(is);
        StringWriter sb = new StringWriter();
        while ((readed = isr.read(buf)) > 0) {
            sb.write(buf, 0, readed);
        }
        return sb.toString();
    }
}
