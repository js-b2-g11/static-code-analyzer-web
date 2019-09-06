/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import java.io.IOException;

import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.CommandLine.ShellCommandException;
import com.philips.bootcamp.analyzerweb.utils.IssueCounter;
import com.philips.bootcamp.analyzerweb.utils.Values;

public class TestDriver {
  public static void main(String args[]) {
	  	final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.FILE_PATH, Values.PMD_RULESET);
        final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer("C:\\Users\\320053825\\Documents\\static-code-analyzer\\helloworld\\src\\main\\java\\com\\philips\\bootcamp\\helloworld\\HelloApp.java", Values.CHECKSTYLE_PATH,
        		Values.CHECKSTYLE_RULESET);
        try {
          StringBuilder cmdOut = checkstyleTool.generateReport();
          System.out.println(cmdOut);
          System.out.println(IssueCounter.countIssuesSingleAnalyzer(cmdOut));
        } catch (final ShellCommandException e) {
          e.printStackTrace();
        }   
  }
}

