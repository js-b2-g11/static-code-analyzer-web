/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import java.io.File;

public class FileValidatorUtil {

  public static boolean isValidPath(final String filepath) {

    boolean exists = false;
    try
    {
      final File tempFile = new File(filepath);

      exists = tempFile.exists();
    }
    catch(final Exception e)
    {
      System.out.println("Hey, an error here!");
      exists = false;
    }
    return exists;
  }

}
