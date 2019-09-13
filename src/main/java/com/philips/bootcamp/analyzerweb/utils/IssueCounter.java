package com.philips.bootcamp.analyzerweb.utils;

public class IssueCounter {
	private IssueCounter() {
	}
	public static int countIssuesSingleAnalyzer(StringBuilder str) {
		String[] lines = str.toString().split("\r\n|\r|\n");
		return lines.length - 2;
	}
	
	public static int countIssuesIntegratedAnalyzer(StringBuilder str) {
		String[] lines = str.toString().split("\r\n|\r|\n");
		return lines.length - 3;
	}
}
