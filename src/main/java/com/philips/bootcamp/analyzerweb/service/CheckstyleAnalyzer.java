/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.ConfigFileReader;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  public StringBuilder generateReport() throws IOException {
    final StringBuilder sbf = new StringBuilder();
    String s = null;
    if (isValidReport()) {
      final String[] cmdCommand = {"java", "-jar", checkstylePath, "-c", checkstyleRuleset, filepath};
      final Runtime rt = Runtime.getRuntime();
      final Process checkstyleProcess = rt.exec(cmdCommand);
      final BufferedReader stdInput = new BufferedReader(new InputStreamReader(checkstyleProcess.getInputStream()));
      while((s=stdInput.readLine())!=null) {
        sbf.append("\n");
        sbf.append(s);
      }
    }
    else
    {
      sbf.append("File error: file not found or incorrect path");
    }
    return sbf;
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
    final StringBuilder builder = new StringBuilder("Checkstyle Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(checkstylePath);
    builder.append(",");
    builder.append(checkstyleRuleset);

    return builder.toString();
  }

}