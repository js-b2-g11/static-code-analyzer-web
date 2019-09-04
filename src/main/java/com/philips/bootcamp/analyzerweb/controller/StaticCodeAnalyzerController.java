/*
cha * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.JavaFileLister;


@RestController
public class StaticCodeAnalyzerController {

  @RequestMapping(value="/api/cs/",method = RequestMethod.GET)
  public ResponseEntity<String> genCheckstyle(@RequestParam("path") String path)throws IOException{
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    final CheckstyleAnalyzer cs = CheckstyleAnalyzer.getObjectFromConfigFile();
    return new ResponseEntity<>(cs.generateReport() ,HttpStatus.OK);
  }

  @RequestMapping(value="/api/pmd/",method = RequestMethod.GET)
  public ResponseEntity<String> genPmd(@RequestParam("path") String path)throws IOException{
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    final PmdAnalyzer pmd = new PmdAnalyzer(filepath,"category/java/codestyle.xml");
    return new ResponseEntity<>(pmd.generateReport(),HttpStatus.OK);
  }

  @RequestMapping(value = "/api", method = RequestMethod.GET)
  public ModelAndView getFiles(@RequestParam("path") String path)throws IOException{
    final JavaFileLister listFiles = new JavaFileLister();
    final ModelAndView model = new ModelAndView("index");
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    model.addObject("lists", listFiles.javaFilefilter(filepath));
    return model;
  }
}
