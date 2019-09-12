package com.philips.bootcamp.analyzerweb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IssueCounter {
	public static int countIssuesCheckstyleAnalyzer(StringBuilder str) {
		String[] lines = str.toString().split("\r\n|\r|\n");
		return lines.length - 2;
	}
	
	public static int countIssuesPmdAnalyzer(StringBuilder str) {
      String[] lines = str.toString().split("\r\n|\r|\n");
      return lines.length;
  }
	
	public static int countIssuesSimianAnalyzer(StringBuilder str) {
	  Pattern p = Pattern.compile("[0-9]+(?= duplicate lines)");
	  Matcher m = p.matcher(str.toString());
	  int simianCount = 0;
	  while (m.find()) {
	    simianCount = Integer.parseInt(m.group());
	  }
	  return simianCount;
	}
	
	public static int countIssuesIntegratedAnalyzer(StringBuilder checkstyleOutput, StringBuilder pmdOutput,
	    StringBuilder simianOutput) {		
		return  countIssuesCheckstyleAnalyzer(checkstyleOutput) + countIssuesPmdAnalyzer(pmdOutput) 
		+ countIssuesSimianAnalyzer(simianOutput);
	}
}
