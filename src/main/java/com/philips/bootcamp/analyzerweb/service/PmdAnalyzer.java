/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.ConfigFileReader;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;
import com.philips.bootcamp.analyzerweb.utils.CommandLineExecutor;

public class PmdAnalyzer extends Tool{

  String pmdRuleset;

  public PmdAnalyzer(String filepath, String pmdRuleset) {
    super(filepath);
    this.pmdRuleset = pmdRuleset;    
  }

  public static PmdAnalyzer getObjectFromConfigFile() {
    final ConfigFileReader reader = ConfigFileReader.getReaderInstance();
    return new PmdAnalyzer(reader.getConfigurationValue("path"),
        reader.getConfigurationValue("pmdRuleset"));
  }

  @Override
  public StringBuilder generateReport() throws FilePathNotValidException, IOException, InterruptedException { 
    if (FileValidator.isValidPath(filepath)) {
      final String[] cmdCommand = {"pmd.bat", "-d", filepath, "-R", pmdRuleset, "-failOnViolation", "false"};
      StringBuilder outputReport = new StringBuilder(CommandLineExecutor.runShellCommand(cmdCommand));
      issueCount = countIssues(outputReport, this);
      return outputReport;
    } else {
      throw new FilePathNotValidException("Error: incorrect path/file not found");
    }
      
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("PMD Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(pmdRuleset);
    builder.append(",");
    builder.append(issueCount);

    return builder.toString();
  }

  @Override
  public int countIssues(StringBuilder report, Tool tool) {
    String[] lines = report.toString().split("\r\n|\r|\n");
    return lines.length;
  }

}
