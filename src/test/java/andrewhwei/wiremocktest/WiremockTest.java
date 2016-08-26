package andrewhwei.wiremocktest;

import java.net.URL;
import java.net.URLConnection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

public class WiremockTest {
//  URL myURL = new URL("/");
//  URLConnection myURLConnection = myURL.openConnection();
//  myURLConnection.connect();
	
	GetRequest jsonResponse = Unirest.get("http://");
  
  @Test
	public void exactUrlOnly() {
		stubFor(get(urlEqualTo("/"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "text/plain")
						.withBody("Hello world!")));
		
		assertThat(testClient.get("/some/thing").statusCode(), is(200));
		assertThat(testClient.get("/some/thing/else").statusCode(), is(404));
	}
}
