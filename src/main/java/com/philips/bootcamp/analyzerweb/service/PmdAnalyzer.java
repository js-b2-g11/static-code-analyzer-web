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
      return new StringBuilder(CommandLineExecutor.runShellCommand(cmdCommand));
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

    return builder.toString();
  }

}
