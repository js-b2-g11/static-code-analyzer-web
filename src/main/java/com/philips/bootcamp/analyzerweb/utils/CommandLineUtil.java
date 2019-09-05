/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import static org.slf4j.LoggerFactory.getLogger;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

public class CommandLineUtil{

  private static final Logger LOGGER = getLogger(CommandLineUtil.class);
  public static String runShellCommand(final String... command) throws  IOException, TimeoutException, InterruptedException {

    final String joinedCommand = String.join(" ", command);
    LOGGER.debug("Executing shell command: `{}`", joinedCommand);
    ProcessResult result=null;
    result = new ProcessExecutor()
        .command(command)
        .readOutput(true)
        .exitValueNormal()
        .execute();
    return result.outputUTF8().trim();
  }

}