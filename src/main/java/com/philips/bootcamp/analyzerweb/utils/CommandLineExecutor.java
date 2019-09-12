package com.philips.bootcamp.analyzerweb.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineExecutor {
  public static StringBuilder runShellCommand(String[] args)
      throws IOException, InterruptedException {    
    ProcessBuilder builder = new ProcessBuilder();
    builder.command(args);
    builder.directory(new File(System.getProperty("user.home")));
    Process process = builder.start();
    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    StringBuilder cmdOutput = new StringBuilder();
    while ((line = br.readLine()) != null) {
      cmdOutput.append(String.format("%s%n", line));
    }
    int exitCode = process.waitFor();
    assert exitCode == 0;
    return cmdOutput;
  }
}
