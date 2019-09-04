/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import java.io.IOException;

import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.Values;

public class TestDriver {
  public static void main(String args[]) {
        final CheckstyleAnalyzer checkstyleTool = CheckstyleAnalyzer.getObjectFromConfigFile();
        final PmdAnalyzer pmdTool = PmdAnalyzer.getObjectFromConfigFile();
        final IntegratedAnalyzer analyzer = new IntegratedAnalyzer(Values.FILE_PATH);
        analyzer.add(checkstyleTool);
        analyzer.add(pmdTool);
        try {
          System.out.println(analyzer.generateReport());
        } catch (final IOException e) {
          e.printStackTrace();
        }
  }
}

