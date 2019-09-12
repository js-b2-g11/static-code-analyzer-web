/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.ProcessExecutorCommandLine.ShellCommandException;
import com.philips.bootcamp.analyzerweb.utils.IssueCounter;
import com.philips.bootcamp.analyzerweb.utils.Values;

public class TestDriver {
  public static void main(String args[]) throws FilePathNotValidException, IOException, InterruptedException  {
     final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
//     final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.CHECKSTYLE_PATH,
//         Values.CHECKSTYLE_RULESET);
//     System.out.println(checkstyleTool.generateReport());
//     System.out.println(pmdTool.generateReport());
    // try {
    // StringBuilder cmdOut = checkstyleTool.generateReport();
    // System.out.println(cmdOut);
    // System.out.println(IssueCounter.countIssuesSingleAnalyzer(cmdOut));
    // } catch (final ShellCommandException e) {
    // e.printStackTrace();
    // }
    // Checkstyle
     pmdTool.generateReport();
    // PMD
    // TestCommandLine.runShellCommand("pmd.bat", "-d", Values.TEST_INVALID_FILE_PATH, "-R",
    // Values.PMD_RULESET, "-failOnViolation", "false");
  }
}

