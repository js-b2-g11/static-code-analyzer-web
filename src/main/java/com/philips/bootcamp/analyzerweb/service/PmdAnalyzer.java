/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;
import com.philips.bootcamp.analyzerweb.utils.CommandLineExecutor;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;


public class PmdAnalyzer extends AbstractTool{


  private final String pmdRuleset;

  public PmdAnalyzer(final String filepath,final String pmdRuleset) {
    super(filepath);
    this.pmdRuleset = pmdRuleset;
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
  public boolean isValidReport() {

    return false;
  }

}