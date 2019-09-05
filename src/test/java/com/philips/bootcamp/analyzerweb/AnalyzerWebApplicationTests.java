/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb;

import static org.junit.Assert.assertEquals;
import java.net.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.philips.bootcamp.analyzerweb.utils.PathEncoder;
import com.philips.bootcamp.analyzerweb.utils.ValuesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyzerWebApplicationTests {


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

}
