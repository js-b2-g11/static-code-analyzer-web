/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFileLister {
  private static final Logger LOGGER = Logger.getLogger( JavaFileLister.class.getName() );
  public List<String> javaFilefilter(final String dir) {
    List<String> result = null;
    try (Stream<Path> walk = Files.walk(Paths.get(dir))) {

      result = walk.map(x -> x.toString())
          .filter(f -> f.endsWith(".java")).collect(Collectors.toList());

    } catch (final IOException e) {
      LOGGER.log(null, "context",e);
    }
    return result;
  }
}
