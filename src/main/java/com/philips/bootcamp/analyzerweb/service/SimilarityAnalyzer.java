package com.philips.bootcamp.analyzerweb.service;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.CommandLineExecutor;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;

public class SimilarityAnalyzer extends Tool {

  String simianPath;
  
  public SimilarityAnalyzer(String filepath, String simianPath) {
    super(filepath);    
    this.simianPath = simianPath;
  }

  @Override
  public StringBuilder generateReport()
      throws FilePathNotValidException, IOException, InterruptedException {
    if (FileValidator.isValidPath(filepath)) {
      if (new File(filepath).isDirectory()) {
        filepath += "*.java";
      }
      final String[] cmdCommand = {"java", "-jar", simianPath, filepath};
      StringBuilder outputReport = new StringBuilder(CommandLineExecutor.runShellCommand(cmdCommand));
      issueCount = countIssues(outputReport, this);
      return outputReport;
    } else {
      throw new FilePathNotValidException("Error: incorrect path/file not found");
    }
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("Similarity Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(simianPath);
    builder.append(",");
    builder.append(issueCount);
    return builder.toString();
  }

  @Override
  public int countIssues(StringBuilder report, Tool tool) {
    Pattern p = Pattern.compile("[0-9]+(?= duplicate lines)");
    Matcher m = p.matcher(report.toString());
    int simianCount = 0;
    while (m.find()) {
      simianCount = Integer.parseInt(m.group());
    }
    return simianCount;
  }

}
