/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.validation.Configuration;

public class ConfigFileReader {

  private static ConfigFileReader instance = null;

  private static final Properties PROPERITES;

  private static final String CONFIG_FILE = "/application.properties";

  private ConfigFileReader() {

  }

  public static ConfigFileReader getReaderInstance() {
    if (instance == null) {
      instance = new ConfigFileReader();
    }
    return instance;
  }

  static {
    PROPERITES = new Properties();
    try (InputStream inputStream = Configuration.class.getResourceAsStream(CONFIG_FILE)) {
      PROPERITES.load(inputStream);
    } catch (final IOException e) {
      throw new RuntimeException("Failed to read file " + CONFIG_FILE, e);
    }
  }

  public static Map<String, String> getConfiguration() {
    // ugly workaround to get String as generics
    final Map temp = PROPERITES;
    @SuppressWarnings("unchecked")
    final Map<String, String> map = new HashMap<>(temp);
    // prevent the returned configuration from being modified
    return Collections.unmodifiableMap(map);
  }


  public String getConfigurationValue(final String key) {
    return PROPERITES.getProperty(key);
  }

}
