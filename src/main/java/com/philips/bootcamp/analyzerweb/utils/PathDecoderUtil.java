/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class PathDecoderUtil{

  public static String decodeURI(final String path) throws UnsupportedEncodingException {
    return java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
  }
}
