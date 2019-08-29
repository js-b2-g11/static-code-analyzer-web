package com.philips.bootcamp.analyzerweb.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IssueCounter {
	public void getIssueCount(String filepath) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> result = null;
		ObjectMapper mapper = new ObjectMapper();
		try (Stream<Path> walk = Files.walk(Paths.get(filepath))) {
			result = walk.map(x -> x.toString()).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < result.size(); i++) {
			FileReader input = new FileReader(result.get(i).replace("\\", "\\\\"));
			BufferedReader bufRead = new BufferedReader(input);
			String myLine = null;

			while ((myLine = bufRead.readLine()) != null) {
				String[] array = myLine.split(" ");

				Pattern p = Pattern.compile("\\[(.*?)\\]$");

				for (int j = 0; j < array.length; j++) {

					Matcher m = p.matcher(array[j]);

					if (m.find()) {
						String s = m.group(1);

						if (!map.containsKey(s)) {
							map.put(s, 1);
						} else {
							int count = map.get(s);
							map.put(s, count + 1);
						}
					}
				}
			}
			bufRead.close();
		}		
		try {
			mapper.writeValue(new File("./reports/result.json"), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
