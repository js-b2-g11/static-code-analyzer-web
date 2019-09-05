/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;
import com.philips.bootcamp.analyzerweb.utils.CommandLineUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckstyleAnalyzer extends AbstractTool{

  private final String checkstylePath;
  private final String checkstyleRuleset;

  public CheckstyleAnalyzer(final String filepath, final String checkstylePath,final String checkstyleRuleset) {
    super(filepath);
    this.checkstylePath = checkstylePath;
    this.checkstyleRuleset = checkstyleRuleset;
  }

  @Override
  public String generateReport() throws IOException, TimeoutException, InterruptedException  {
    String cmdOutput = null;
    final String[] cmdCommand = {"java", "-jar", checkstylePath, "-c", checkstyleRuleset, filepath};
    cmdOutput = CommandLineUtil.runShellCommand(cmdCommand);
    return cmdOutput;
  }

  @Override
  public boolean isValidReport() {
    return false;
  }


}