/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.CommandLine;
import com.philips.bootcamp.analyzerweb.utils.CommandLine.ShellCommandException;
import com.philips.bootcamp.analyzerweb.utils.ConfigFileReader;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;

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
  public String generateReport() throws RuntimeException {
    if (isValidReport()) {
      final String[] cmdCommand = {"pmd.bat", "-d", filepath, "-f", "html", "-R", pmdRuleset, "-failOnViolation", "false"};
      return CommandLine.runShellCommand(cmdCommand);
    } else {
      return "File error: file not found or incorrect path";
    }
  }

  @Override
  public boolean isValidReport() {
    if (this.getFilepath() == null || this.getFilepath().equals("")) {
      return false;
    }
    return (FileValidator.isValidPath(this.getFilepath()));
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("PMD Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(pmdRuleset);

    return builder.toString();
  }

}
