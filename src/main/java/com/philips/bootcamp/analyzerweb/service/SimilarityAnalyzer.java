package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
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
      final String[] cmdCommand = {"java", "-jar", simianPath, filepath+"*.java"};
      return new StringBuilder(CommandLineExecutor.runShellCommand(cmdCommand));
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

    return builder.toString();
  }

}
