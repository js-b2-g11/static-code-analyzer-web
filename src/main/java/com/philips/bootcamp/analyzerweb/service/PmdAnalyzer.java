/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;
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
  public StringBuilder generateReport() throws IOException {
    final StringBuilder sbf = new StringBuilder();
    if (isValidReport()) {
      String consoleOutput = null;
      final String[] cmdCommand = {"pmd.bat", "-d", filepath, "-f", "html", "-R", pmdRuleset, "-failOnViolation", "false"};
      final Runtime runTime = Runtime.getRuntime();
      final Process checkstyleProcess = runTime.exec(cmdCommand);
      final BufferedReader stdInput = new BufferedReader(new InputStreamReader(checkstyleProcess.getInputStream()));
      while((consoleOutput=stdInput.readLine())!=null) {
        sbf.append("\n");
        sbf.append(consoleOutput);

      }
    }
    else {
      sbf.append("File error: file not found or incorrect path");
    }
    return sbf;
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