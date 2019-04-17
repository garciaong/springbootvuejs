package my.com.etiqa.dsawsclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EndpointTests {

	public static void main(String [] args) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://172.29.75.205:7010/dsarws/ws/prta");

		String input = "{" + 
				"	\"quotation_date\":\"2018-10-01\"," + 
				"	\"year\":\"2018\"," + 
				"	\"language\":\"E\"," + 
				"	\"birth_date\":\"1990-10-01\"," + 
				"	\"gender\":\"M\"," + 
				"	\"smoker_indicator\":\"N\"," + 
				"	\"staff_indicator\":\"C\"," + 
				"	\"occ\":\"1\"," + 
				"	\"loan_amount\":\"150000\"," + 
				"	\"loan_period\":\"20\"," + 
				"	\"cover_period\":\"15\"," + 
				"	\"current_blr\":\"35.0\"," + 
				"	\"financing\":\"N\"," + 
				"	\"servicing\":\"5\"," + 
				"	\"stamp_duty_indicator\":\"N\"" + 
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("REMOTE_TOKEN", "ZHNwdG9kc2F1YXQ=");
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		System.out.println(result.getBody());
	}
	
}
