package com.philips.bootcamp.analyzerweb.other;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFileGetter {
	public List<String> getFile(String filepath) {
		List<String> result = null;
		try (Stream<Path> walk = Files.walk(Paths.get(filepath))) 
		{
			result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".java")).collect(Collectors.toList());			
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	return result;
}
}

