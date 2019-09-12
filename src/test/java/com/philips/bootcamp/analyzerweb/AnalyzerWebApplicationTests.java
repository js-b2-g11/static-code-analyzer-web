/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.IntegratedAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.service.SimilarityAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.ProcessExecutorCommandLine;
import com.philips.bootcamp.analyzerweb.utils.PathEncoder;
import com.philips.bootcamp.analyzerweb.utils.Values;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyzerWebApplicationTests {

  @Test
  public void generateReport_CheckstyleAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws InterruptedException, FilePathNotValidException, IOException {
      CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_VALID_FILE_PATH, Values.CHECKSTYLE_PATH,
              Values.CHECKSTYLE_RULESET);
      StringBuilder output = null;      
      output = checkstyleTool.generateReport();
      assertTrue(output.length() > 0);
  }
  
  @Test
  public void generateReport_PmdAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
      PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_VALID_FILE_PATH, Values.PMD_RULESET);
      StringBuilder output = null;
      output = pmdTool.generateReport();
      assertTrue(output.length() > 0);
  }
  
  @Test
  public void generateReport_SimilarityAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
      SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
      StringBuilder output = null;
      output = simianTool.generateReport();
      assertTrue(output.length() > 0);
  }
  
  @Test
  public void generateReport_IntegratedAnalyzer_ValidFilePath_GeneratesReportSuccessfully() throws FilePathNotValidException, IOException, InterruptedException {
      PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_VALID_FILE_PATH, Values.PMD_RULESET);
      CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_VALID_FILE_PATH, Values.CHECKSTYLE_PATH,
          Values.CHECKSTYLE_RULESET);
      SimilarityAnalyzer simAnalyzer = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
      IntegratedAnalyzer integratedAnalyzer = new IntegratedAnalyzer(Values.TEST_VALID_FILE_PATH);
      integratedAnalyzer.add(pmdTool);
      integratedAnalyzer.add(checkstyleTool);
      integratedAnalyzer.add(simAnalyzer);
      StringBuilder output = null;
      output = integratedAnalyzer.generateReport();
      assertTrue(output.length() > 0);
  }
	@Test
	public void generateReport_APIController_PmdAnalyzer_ValidFilePath_GenerateWithOKSTatus() throws Exception {
		final String testUri = "/api/pmd/?path=" + PathEncoder.encodeURI(Values.TEST_VALID_FILE_PATH);
		final RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080" + testUri;
		final URI uri = new URI(baseUrl);
		final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void generateReport_APIController_CheckstyleAnalyzer_ValidFilePath_GenerateWithOKSTatus() throws Exception {
		final String testUri = "/api/cs/?path=" + PathEncoder.encodeURI(Values.TEST_VALID_FILE_PATH);
		final RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080" + testUri;
		final URI uri = new URI(baseUrl);
		final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
    public void generateReport_APIController_SimilarityAnalyzer_ValidFilePath_GenerateWithOKSTatus() throws Exception {
        final String testUri = "/api/sim/?path=" + PathEncoder.encodeURI(Values.TEST_VALID_FILE_PATH);
        final RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080" + testUri;
        final URI uri = new URI(baseUrl);
        final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

	@Test(expected = HttpClientErrorException.class)
	public void generateReport_APIController_PmdAnalyzer_InvalidFilePath_GenerateWith404STatus() throws Exception {
		final String testUri = "/api/pmd/?path=" + PathEncoder.encodeURI(Values.TEST_INVALID_FILE_PATH);
		final RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080" + testUri;
		final URI uri = new URI(baseUrl);
		ResponseEntity<String> result = null;
		result = restTemplate.getForEntity(uri, String.class);
		assertEquals(404, result.getStatusCodeValue());
	}

	@Test(expected = HttpClientErrorException.class)
	public void generateReport_APIController_CheckstyleAnalyzer_InvalidFilePath_GenerateWith404STatus() throws UnsupportedEncodingException, URISyntaxException {
		final String testUri = "/api/cs/?path=" + PathEncoder.encodeURI(Values.TEST_INVALID_FILE_PATH);
		final RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080" + testUri;
		final URI uri = new URI(baseUrl);
		ResponseEntity<String> result = null;
		result = restTemplate.getForEntity(uri, String.class);
		assertEquals(404, result.getStatusCodeValue());
	}
	
	@Test(expected = HttpClientErrorException.class)
    public void generateReport_APIController_SimilarityAnalyzer_InvalidFilePath_GenerateWith404STatus() throws UnsupportedEncodingException, URISyntaxException {
        final String testUri = "/api/sim/?path=" + PathEncoder.encodeURI(Values.TEST_INVALID_FILE_PATH);
        final RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080" + testUri;
        final URI uri = new URI(baseUrl);
        ResponseEntity<String> result = null;
        result = restTemplate.getForEntity(uri, String.class);
        assertEquals(404, result.getStatusCodeValue());
    }

	@Test(expected = FilePathNotValidException.class)
	public void generateReport_CheckstyleAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
		CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.CHECKSTYLE_PATH,
				Values.CHECKSTYLE_RULESET);
		checkstyleTool.generateReport();
	}
	
	@Test(expected = FilePathNotValidException.class)
	public void generateReport_PmdAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
		PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
		pmdTool.generateReport();
	}
	
	@Test(expected = FilePathNotValidException.class)
    public void generateReport_SimilarityAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
        SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.SIMIAN_PATH);
        simianTool.generateReport();
    }
	
	@Test(expected = FilePathNotValidException.class)
	public void generateReport_IntegratedAnalyzer_InvalidFilePath_ExceptionThrown() throws FilePathNotValidException, IOException, InterruptedException {
		PmdAnalyzer pmdTool = new PmdAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.PMD_RULESET);
		CheckstyleAnalyzer checkstyleTool = new CheckstyleAnalyzer(Values.TEST_INVALID_FILE_PATH, Values.CHECKSTYLE_PATH,
				Values.CHECKSTYLE_RULESET);
		SimilarityAnalyzer simianTool = new SimilarityAnalyzer(Values.TEST_VALID_FILE_PATH, Values.SIMIAN_PATH);
		IntegratedAnalyzer integratedAnalyzer = new IntegratedAnalyzer(Values.TEST_INVALID_FILE_PATH);
		integratedAnalyzer.add(checkstyleTool);
		integratedAnalyzer.add(pmdTool);
		integratedAnalyzer.add(simianTool);
		integratedAnalyzer.generateReport();
	}	
}
