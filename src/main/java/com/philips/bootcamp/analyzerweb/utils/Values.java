/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

public class Values {

  public static final String FILE_PATH = "C:\\Users\\320053825\\Downloads\\SampleJavaFiles";

  public static final String PMD_CMD = "pmd.bat -d ";
  public static final String PMD_RULESET = "category/java/codestyle.xml";
  public static final String PMD_OUTPUT_FILE = "reportPmd.txt";

  public static final String CHECKSTYLE_PATH = "C:/Checkstyle/checkstyle-8.22-all.jar";
  public static final String CHECKSTYLE_RULESET = "/google_checks.xml";
  public static final String CHECKSTYLE_CMD = "cmd /c java -jar ";
  public static final String CHECKSTYLE_OUTPUT_FILE = "reportCheckStyle.txt";

  public static final int DEFAULT_LINECOUNT = 17;
  public static final String FINAL_OUTPUT_FILE = "mergedReport.txt";

}