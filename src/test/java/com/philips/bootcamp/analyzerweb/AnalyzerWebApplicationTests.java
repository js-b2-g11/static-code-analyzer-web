/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.net.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.philips.bootcamp.analyzerweb.service.CheckstyleAnalyzer;
import com.philips.bootcamp.analyzerweb.service.PmdAnalyzer;
import com.philips.bootcamp.analyzerweb.utils.PathEncoder;
import com.philips.bootcamp.analyzerweb.utils.ValuesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyzerWebApplicationTests {

  @Test
  public void getJavaFileList() throws Exception{
    final String testUri = "/api/?path="+PathEncoder.encodeURI(ValuesUtil.TEST_VALID_PATH);
    final RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8080" + testUri;
    final URI uri = new URI(baseUrl);
    final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void getReportOfValidFileDirectoryForPmd() throws Exception{
    final String testUri = "/api/pmd/?path="+PathEncoder.encodeURI(ValuesUtil.TEST_VALID_PATH);
    final RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8080" + testUri;
    final URI uri = new URI(baseUrl);
    final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void getReportOfValidFileDirectoryForCheckstyle() throws Exception{
    final String testUri = "/api/cs/?path="+PathEncoder.encodeURI(ValuesUtil.TEST_VALID_PATH);
    final RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8080" + testUri;
    final URI uri = new URI(baseUrl);
    final ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
    assertEquals(200, result.getStatusCodeValue());
  }

  @Test(expected = HttpClientErrorException.class)
  public void getReportOfInvalidFileDirectoryForPmd() throws Exception{
    final String testUri = "/api/pmd/?path="+PathEncoder.encodeURI(ValuesUtil.TEST_INVALID_PATH);
    final RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8080" + testUri;
    final URI uri = new URI(baseUrl);
    ResponseEntity<String> result = null;
    result = restTemplate.getForEntity(uri, String.class);
    assertEquals(404, result.getStatusCodeValue());
  }


  @Test(expected = HttpClientErrorException.class)
  public void getReportOfInvalidFileDirectoryForCheckstyle() throws Exception{
    final String testUri = "/api/cs/?path="+PathEncoder.encodeURI(ValuesUtil.TEST_INVALID_PATH);
    final RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8080" + testUri;
    final URI uri = new URI(baseUrl);
    ResponseEntity<String> result = null;
    result = restTemplate.getForEntity(uri, String.class);
    assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void getReportOfValidFileDirectoryForPmd_Console() throws Exception{
    final PmdAnalyzer pmd = new PmdAnalyzer(ValuesUtil.TEST_VALID_PATH,ValuesUtil.PMD_RULESET);
    final StringBuilder output = pmd.generateReport();
    assertTrue(output.length()>0);
  }

  @Test(expected = org.zeroturnaround.exec.InvalidExitValueException.class)
  public void getReportNoFileDirectoryForPmd_Console() throws Exception{
    final PmdAnalyzer pmd = new PmdAnalyzer(ValuesUtil.TEST_EMPTY_PATH,ValuesUtil.PMD_RULESET);
    final StringBuilder output = pmd.generateReport();
    assertTrue(output.length()>0);
  }

  @Test(expected = org.zeroturnaround.exec.InvalidExitValueException.class)
  public void getReportOfInvalidFileDirectoryForPmd_Console() throws Exception{
    final PmdAnalyzer pmd = new PmdAnalyzer(ValuesUtil.TEST_INVALID_PATH,ValuesUtil.PMD_RULESET);
    final StringBuilder output = pmd.generateReport();
    assertTrue(output.length()>0);
  }

  @Test
  public void getReportOfValidFileDirectoryForCheckstyle_Console() throws Exception{
    final CheckstyleAnalyzer cs = new CheckstyleAnalyzer(ValuesUtil.TEST_VALID_PATH,ValuesUtil.CS_PATH,ValuesUtil.CS_RULESET);
    final StringBuilder output = cs.generateReport();
    assertTrue(output.length()>0);
  }

  @Test(expected = org.zeroturnaround.exec.InvalidExitValueException.class)
  public void getReportNoFileDirectoryForCheckstyle_Console() throws Exception{
    final CheckstyleAnalyzer cs = new CheckstyleAnalyzer(ValuesUtil.TEST_EMPTY_PATH,ValuesUtil.CS_PATH,ValuesUtil.CS_RULESET);
    final StringBuilder output = cs.generateReport();
    assertTrue(output.length()>0);
  }

  @Test(expected = org.zeroturnaround.exec.InvalidExitValueException.class)
  public void getReportOfInvalidFileDirectoryForCheckstyle_Console() throws Exception{
    final CheckstyleAnalyzer cs = new CheckstyleAnalyzer(ValuesUtil.TEST_INVALID_PATH,ValuesUtil.CS_PATH,ValuesUtil.CS_RULESET);
    final StringBuilder output = cs.generateReport();
    assertTrue(output.length()>0);
  }

}
