/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import com.philips.bootcamp.analyzerweb.utils.CommandLineExecutor;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.ProcessExecutorCommandLine;
import com.philips.bootcamp.analyzerweb.utils.ProcessExecutorCommandLine.ShellCommandException;
import com.philips.bootcamp.analyzerweb.utils.ConfigFileReader;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;

public class CheckstyleAnalyzer extends Tool{

  private final String checkstylePath;
  private final String checkstyleRuleset;

  public CheckstyleAnalyzer(String filepath, String checkstylePath, String checkstyleRuleset) {
    super(filepath);
    this.checkstylePath = checkstylePath;
    this.checkstyleRuleset = checkstyleRuleset;
  }

  public static CheckstyleAnalyzer getObjectFromConfigFile() {
    final ConfigFileReader reader = ConfigFileReader.getReaderInstance();
    return new CheckstyleAnalyzer(reader.getConfigurationValue("path"),
        reader.getConfigurationValue("checkstylePath"),
        reader.getConfigurationValue("checkstyleRuleset"));
  }

  @Override
  public StringBuilder generateReport() throws FilePathNotValidException, IOException, InterruptedException {
    if (FileValidator.isValidPath(filepath)) {
      final String[] cmdCommand = {"java", "-jar", checkstylePath, "-c", checkstyleRuleset, filepath};
      return new StringBuilder(CommandLineExecutor.runShellCommand(cmdCommand));
    } else {
      throw new FilePathNotValidException("Error: incorrect path/file not found"); 
    }    
  } 

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("Checkstyle Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(checkstylePath);
    builder.append(",");
    builder.append(checkstyleRuleset);

    return builder.toString();
  }

}
