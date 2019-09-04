/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;
import com.philips.bootcamp.analyzerweb.utils.CommandLineUtil;
import com.philips.bootcamp.analyzerweb.utils.ConfigFileReader;
import com.philips.bootcamp.analyzerweb.utils.FileValidatorUtil;

public class PmdAnalyzer extends AbstractTool{


  private final String pmdRuleset;

  public PmdAnalyzer(final String filepath,final String pmdRuleset) {
    super(filepath);
    this.pmdRuleset = pmdRuleset;
  }

  public static PmdAnalyzer getObjectFromConfigFile() {
    final ConfigFileReader reader = ConfigFileReader.getReaderInstance();
    return new PmdAnalyzer(reader.getConfigurationValue("path"),
        reader.getConfigurationValue("pmdRuleset"));
  }

  @Override
  public String generateReport() throws IOException {
    String cmdOuput = null;

    try {
      final String[] cmdCommand = {"pmd.bat", "-d", filepath, "-f", "html", "-R", pmdRuleset, "-failOnViolation", "false"};
      cmdOuput =  CommandLineUtil.runShellCommand(cmdCommand);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return cmdOuput;
  }

  @Override
  public boolean isValidReport() {
    boolean flag = false;
    if (this.getFilepath() == null || this.getFilepath().equals("")) {
      flag = false;
    }
    else {
      flag = FileValidatorUtil.isValidPath(this.getFilepath());
    }
    return flag;
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