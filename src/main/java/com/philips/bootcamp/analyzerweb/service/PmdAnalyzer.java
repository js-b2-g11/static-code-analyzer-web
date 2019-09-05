/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;
import com.philips.bootcamp.analyzerweb.utils.CommandLineUtil;

public class PmdAnalyzer extends AbstractTool{


  private final String pmdRuleset;

  public PmdAnalyzer(final String filepath,final String pmdRuleset) {
    super(filepath);
    this.pmdRuleset = pmdRuleset;
  }

  @Override
  public String generateReport() throws IOException, TimeoutException, InterruptedException {
    String cmdOutput = null;
    final String[] cmdCommand = {"pmd.bat", "-d", filepath, "-f", "html", "-R", pmdRuleset, "-failOnViolation", "false"};
    cmdOutput = CommandLineUtil.runShellCommand(cmdCommand);
    return cmdOutput;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("PMD Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(pmdRuleset);

    return builder.toString();
  }

  @Override
  public boolean isValidReport() {
    // TODO Auto-generated method stub
    return false;
  }

}