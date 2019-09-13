/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.utils;

public class IssueCounter {
  public static int countIssuesSingleAnalyzer(StringBuilder str) {
    final String[] lines = str.toString().split("\r\n|\r|\n");
    return lines.length - 2;
  }

  public static int countIssuesIntegratedAnalyzer(StringBuilder str) {
    final String[] lines = str.toString().split("\r\n|\r|\n");
    return lines.length - 4;
  }
}