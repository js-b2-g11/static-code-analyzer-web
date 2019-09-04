/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import static org.slf4j.LoggerFactory.getLogger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

public class CommandLineUtil{

  private static final Logger LOGGER = getLogger(CommandLineUtil.class);
  public static String runShellCommand(final String... command) {

    final String joinedCommand = String.join(" ", command);
    LOGGER.debug("Executing shell command: `{}`", joinedCommand);

    try {
      ProcessResult result;
      result = new ProcessExecutor()
          .command(command)
          .readOutput(true)
          .exitValueNormal()
          .execute();

      return result.outputUTF8().trim();
    } catch (IOException | InterruptedException | TimeoutException | InvalidExitValueException e) {
      throw new ShellCommandException("Exception when executing " + joinedCommand, e);
    }
  }

  public static boolean executableExists(final String executable) {
    boolean exists = false;
    final File directFile = new File(executable);
    if (directFile.exists() && directFile.canExecute()) {
      exists = true;
    }

    for (final String pathString : getSystemPath()) {
      final Path path = Paths.get(pathString);
      if (Files.exists(path.resolve(executable)) && Files.isExecutable(path.resolve(executable))) {
        exists= true;
      }
    }
    return exists;
  }

  @NotNull
  public static String[] getSystemPath() {
    return System.getenv("PATH").split(Pattern.quote(File.pathSeparator));
  }

  private static class ShellCommandException extends RuntimeException {
    public ShellCommandException(final String message,final Exception e) {
      super(message, e);
    }
  }
}