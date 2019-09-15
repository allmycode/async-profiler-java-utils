package conterouz.profile;

import conterouz.profile.ProcessResult;
import conterouz.profile.ProcessUtils;

import java.io.IOException;

public class SimpleProcessRunner {
    public static void main(String[] args) {
        String cmd = "profiler.sh";
        try {
            ProcessResult pwdResult = ProcessUtils.run(cmd.split(" "));
            pwdResult.print();
        } catch (IOException e) {
            System.err.println("Error running process `" + cmd + "`");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waitong for result");
        }
    }

}
