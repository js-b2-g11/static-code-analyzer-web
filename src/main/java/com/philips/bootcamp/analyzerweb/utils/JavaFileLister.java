/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFileLister {

  public List<String> javaFilefilter(String dir){
    List<String> result = null;
    final Base64.Encoder encoder = Base64.getUrlEncoder();
    try (final Stream<Path> walk = Files.walk(Paths.get(dir))) {

      result = walk.map(x -> x.toString())
          .filter(f -> f.endsWith(".java")).collect(Collectors.toList());

    } catch (final IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}