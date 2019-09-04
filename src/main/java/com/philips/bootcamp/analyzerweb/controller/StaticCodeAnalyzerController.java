/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
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
import com.philips.bootcamp.analyzerweb.utils.PathDecoder;


@RestController
public class StaticCodeAnalyzerController {

  @RequestMapping(value="/api/cs/",method = RequestMethod.GET)
  public ResponseEntity<StringBuilder> genCheckstyle(@RequestParam("path")final String path)throws IOException{
    final String filepath = PathDecoder.decodeURI(path);
    final CheckstyleAnalyzer checkStyle = new CheckstyleAnalyzer(filepath,"C:/Checkstyle/checkstyle-8.22-all.jar","/google_checks.xml");
    return new ResponseEntity<>(checkStyle.generateReport(),HttpStatus.OK);
  }

  @RequestMapping(value="/api/pmd/",method = RequestMethod.GET)
  public ResponseEntity<StringBuilder> genPmd(@RequestParam("path")final String path)throws IOException{
    final String filepath = PathDecoder.decodeURI(path);
    final PmdAnalyzer pmd = new PmdAnalyzer(filepath,"category/java/codestyle.xml");
    return new ResponseEntity<>(pmd.generateReport(),HttpStatus.OK);
  }

  @RequestMapping(value = "/api", method = RequestMethod.GET)
  public ModelAndView getFiles(@RequestParam("path")final String path)throws IOException{
    final JavaFileLister listFiles = new JavaFileLister();
    final ModelAndView model = new ModelAndView("index");
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    model.addObject("lists", listFiles.javaFilefilter(filepath));
    return model;
  }
}
