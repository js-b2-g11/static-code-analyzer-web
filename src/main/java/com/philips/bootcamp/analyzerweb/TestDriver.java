/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.service.SimilarityAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.ProcessExecutorCommandLine.ShellCommandException;
import com.philips.bootcamp.analyzerweb.utils.CommandLineExecutor;
import com.philips.bootcamp.analyzerweb.utils.IssueCounter;
import com.philips.bootcamp.analyzerweb.utils.Values;

public class TestDriver {
  public static void main(String args[]) throws FilePathNotValidException, IOException, InterruptedException  {
//     final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
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
    SimilarityAnalyzer sim = new SimilarityAnalyzer(Values.FILE_PATH, Values.SIMIAN_PATH);
    sim.generateReport();
    System.out.println(sim.getIssueCount());
//    StringBuilder str = new StringBuilder("Similarity Analyser 2.5.10 - http://www.harukizaemon.com/simian\r\n" + 
//        "Copyright (c) 2003-2018 Simon Harris.  All rights reserved.\r\n" + 
//        "Simian is not free unless used solely for non-commercial or evaluation purposes.\r\n" + 
//        "{failOnDuplication=true, ignoreCharacterCase=true, ignoreCurlyBraces=true, ignoreIdentifierCase=true, ignoreModifiers=true, ignoreStringCase=true, threshold=6}\r\n" + 
//        "Found 6 duplicate lines with fingerprint ec191fbddb7855c0e728c0d9217ee72d in the following files:\r\n" + 
//        " Between lines 58 and 63 in C:\\Users\\320053825\\Downloads\\SampleJavaFiles\\AnalyzerWebApplicationTests.java\r\n" + 
//        " Between lines 46 and 51 in C:\\Users\\320053825\\Downloads\\SampleJavaFiles\\AnalyzerWebApplicationTests.java\r\n" + 
//        "Found 12 duplicate lines in 2 blocks in 1 files\r\n" + 
//        "Processed a total of 70 significant (131 raw) lines in 4 files\r\n" + 
//        "Processing time: 0.068sec\r\n");
//     int count = IssueCounter.countIssuesSimianAnalyzer(str);
//     System.out.println(count);
    // PMD
    // TestCommandLine.runShellCommand("pmd.bat", "-d", Values.TEST_INVALID_FILE_PATH, "-R",
    // Values.PMD_RULESET, "-failOnViolation", "false");
  }
}

