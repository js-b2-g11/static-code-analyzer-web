/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
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
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.JavaFileLister;
import com.philips.bootcamp.analyzerweb.utils.PathDecoderUtil;
import com.philips.bootcamp.analyzerweb.utils.ValuesUtil;;


@RestController
public class StaticCodeAnalyzerController {

  @GetMapping("/api/cs/")
  public ResponseEntity<String> genCheckstyle(@RequestParam("path")final String path)throws IOException{
    final String filepath = PathDecoderUtil.decodeURI(path);
    try {
      final CheckstyleAnalyzer checkStyle = new CheckstyleAnalyzer(filepath,ValuesUtil.CS_PATH,ValuesUtil.CS_RULESET);
      return new ResponseEntity<>(checkStyle.generateReport(),HttpStatus.OK);
    } catch (final Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/api/pmd/")
  public ResponseEntity<String> genPmd(@RequestParam("path")final String path)throws IOException{
    final String filepath = PathDecoderUtil.decodeURI(path);
    try {
      final PmdAnalyzer pmd = new PmdAnalyzer(filepath,ValuesUtil.PMD_RULESET);
      return new ResponseEntity<>(pmd.generateReport(),HttpStatus.OK);
    } catch (final Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/api")
  public ModelAndView getFiles(@RequestParam("path")final String path)throws IOException{
    final JavaFileLister listFiles = new JavaFileLister();
    final ModelAndView model = new ModelAndView("index");
    final String filepath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
    model.addObject("lists", listFiles.javaFilefilter(filepath));
    return model;
  }
}
