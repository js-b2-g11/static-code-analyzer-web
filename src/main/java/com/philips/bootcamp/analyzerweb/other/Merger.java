/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.other;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;

public class Merger{
  public void genAndMergeFile(String filepath) throws IOException, InterruptedException {
    if(FileValidator.isValidPath(filepath)) {

      System.out.print("Valid file path detected!\n");
      final PmdReportGenerator reportPmd = PmdReportGenerator.getPmdReportObject(filepath);
      reportPmd.generateReport();
      final CheckstyleReportGenerator reportCheckstyle = CheckstyleReportGenerator.getCheckstyleReportObject(filepath);
      reportCheckstyle.generateReport();
    }
    else {
      System.out.println("Invalid file path specified");
    }
  }
}
