/*
cha * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.IssueCounter;
import com.philips.bootcamp.analyzerweb.utils.JavaFileLister;
import com.philips.bootcamp.analyzerweb.utils.Values;


@RestController
public class StaticCodeAnalyzerController {
  
  @GetMapping("/api/cs/")
  public ResponseEntity<StringBuilder> genCheckstyle(@RequestParam("path") String path) throws IOException {
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    final CheckstyleAnalyzer cs = new CheckstyleAnalyzer(filepath, Values.CHECKSTYLE_PATH, 
    		Values.CHECKSTYLE_RULESET);    
    try {
		return new ResponseEntity<>(cs.generateReport() ,HttpStatus.OK);
	} catch (RuntimeException e) {
		return new ResponseEntity<>(new StringBuilder(Values.ERROR_FILE_NOT_FOUND) ,HttpStatus.NOT_FOUND);
	}
  }

@GetMapping("/api/pmd/")
  public ResponseEntity<StringBuilder> genPmd(@RequestParam("path") String path)throws IOException{

    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    final PmdAnalyzer pmd = new PmdAnalyzer(filepath, Values.PMD_RULESET);
    try {
		return new ResponseEntity<>(pmd.generateReport() ,HttpStatus.OK);
	} catch (RuntimeException e) {
		return new ResponseEntity<>(new StringBuilder(Values.ERROR_FILE_NOT_FOUND),HttpStatus.NOT_FOUND);
	}
  }
  
  @GetMapping("/api/all/")
  public ResponseEntity<StringBuilder> genIntegratedReport(@RequestParam("path") String path)throws IOException{
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    final CheckstyleAnalyzer checkstyleAnalyzer = new CheckstyleAnalyzer(filepath, Values.CHECKSTYLE_PATH, 
    		Values.CHECKSTYLE_RULESET);
    final PmdAnalyzer pmdAnalyzer = new PmdAnalyzer(filepath, Values.PMD_RULESET);
    IntegratedAnalyzer integratedAnalyzer = new IntegratedAnalyzer(filepath);
    integratedAnalyzer.add(checkstyleAnalyzer);
    integratedAnalyzer.add(pmdAnalyzer);
    try {
    	StringBuilder output = integratedAnalyzer.generateReport();
    	int countOfIssues = IssueCounter.countIssuesIntegratedAnalyzer(output);
    	output.insert(0, String.format("Count of Issues = %d%n", countOfIssues));
    	if (countOfIssues == 0) {
    		output.insert(0, "GO\n");
    	} else {
    		output.insert(0, "NO-GO\n");
    	}
    	return new ResponseEntity<>(output ,HttpStatus.OK);
	} catch (RuntimeException e) {
		return new ResponseEntity<>(new StringBuilder(Values.ERROR_FILE_NOT_FOUND), HttpStatus.NOT_FOUND);
	}
  }

  
  @GetMapping("/api")
  public ModelAndView getFiles(@RequestParam("path") String path)throws IOException{
    final JavaFileLister listFiles = new JavaFileLister();
    final ModelAndView model = new ModelAndView("index");
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    model.addObject("lists", listFiles.javaFilefilter(filepath));
    return model;
  }
}
