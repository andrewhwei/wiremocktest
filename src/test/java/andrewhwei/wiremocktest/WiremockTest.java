package andrewhwei.wiremocktest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.jayway.restassured.RestAssured.*;

public class WiremockTest {

	public void setupStub() {	         
	    stubFor(get(urlEqualTo("/an/endpoint"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", "text/plain")
	                .withStatus(200)
	                .withBody("You've reached a valid WireMock endpoint")));
	}
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);
	
	@Test
	public void testStatusCodePositive() {
	    setupStub();
	         
	    given().
	    when().
	        get("http://localhost:8090/an/endpoint").
	    then().
	        assertThat().statusCode(200);
	}
	     
	@Test
	public void testStatusCodeNegative() {
	    setupStub();
	         
	    given().
	    when().
	        get("http://localhost:8090/another/endpoint").
	    then().
	        assertThat().statusCode(404);
	}
	     
	@Test
	public void testResponseContents() {
	    setupStub();
	     
	    String response = get("http://localhost:8090/an/endpoint").asString();
	    Assert.assertEquals("You've reached a valid WireMock endpoint", response);
	}
}
