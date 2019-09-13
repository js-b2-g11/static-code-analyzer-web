/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.service.SimilarityAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.JavaFileLister;
import com.philips.bootcamp.analyzerweb.utils.Values;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyzerWebApplicationTests {

  @Test
  public void generateReport_CheckstyleAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws InterruptedException, FilePathNotValidException, IOException {
    final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_VALID_FILE_PATH, Values.CHECKSTYLE_PATH,
        Values.CHECKSTYLE_RULESET);
    StringBuilder output = null;
    output = checkstyleTool.generateReport();
    assertTrue(output.length() > 0);
  }

  @Test
  public void generateReport_PmdAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
    final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_VALID_FILE_PATH, Values.PMD_RULESET);
    StringBuilder output = null;
    output = pmdTool.generateReport();
    assertTrue(output.length() > 0);
  }

  @Test
  public void generateReport_SimilarityAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
    final SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
    StringBuilder output = null;
    output = simianTool.generateReport();
    assertTrue(output.length() > 0);
  }

  @Test
  public void generateReport_IntegratedAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
    final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_VALID_FILE_PATH, Values.PMD_RULESET);
    final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_VALID_FILE_PATH, Values.CHECKSTYLE_PATH,
        Values.CHECKSTYLE_RULESET);
    final SimilarityAnalyzer simAnalyzer = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
    final IntegratedAnalyzer integratedAnalyzer = new IntegratedAnalyzer(Values.TEST_VALID_FILE_PATH);
    integratedAnalyzer.add(pmdTool);
    integratedAnalyzer.add(checkstyleTool);
    integratedAnalyzer.add(simAnalyzer);
    StringBuilder output = null;
    output = integratedAnalyzer.generateReport();
    assertTrue(output.length() > 0);
  }

  @Test(expected = FilePathNotValidException.class)
  public void generateReport_CheckstyleAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
    final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.CHECKSTYLE_PATH,
        Values.CHECKSTYLE_RULESET);
    checkstyleTool.generateReport();
  }

  @Test(expected = FilePathNotValidException.class)
  public void generateReport_PmdAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
    final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
    pmdTool.generateReport();
  }

  @Test(expected = FilePathNotValidException.class)
  public void generateReport_SimilarityAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
    final SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.SIMIAN_PATH);
    simianTool.generateReport();
  }

  @Test(expected = FilePathNotValidException.class)
  public void generateReport_IntegratedAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
    final PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
    final CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.CHECKSTYLE_PATH,
        Values.CHECKSTYLE_RULESET);
    final SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
    final IntegratedAnalyzer integratedAnalyzer = new IntegratedAnalyzer(Values.TEST_INVALID_FILE_PATH);
    integratedAnalyzer.add(checkstyleTool);
    integratedAnalyzer.add(pmdTool);
    integratedAnalyzer.add(simianTool);
    integratedAnalyzer.generateReport();
  }
  @Test
  public void getJavaFileListValidDirectory() throws Exception{
    final JavaFileLister jfl = new JavaFileLister();
    final List<String> fileList=jfl.javaFilefilter(Values.TEST_VALID_FILE_PATH);
    assertTrue(fileList.size()>0);
  }

  @Test(expected = NullPointerException.class)
  public void getJavaFileListInvalidDirectory() throws Exception{
    final JavaFileLister jfl = new JavaFileLister();
    final List<String> fileList=jfl.javaFilefilter(Values.TEST_INVALID_FILE_PATH);
    assertTrue(fileList.size()== 0);
  }
}
