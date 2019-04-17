package my.com.etiqa.dsawsclient.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import my.com.etiqa.dsawsclient.model.ClientConfig;

@Service
@CacheConfig(cacheNames = { "clientEndpointCache" }) // Tells Spring where to store cache
public class DsawsclientService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${dsa.ws.prta.url}")
	private String prta_url;
	
	@Value("${dsa.ws.prtt.url}")
	private String prtt_url;
	
	@Value("${dsa.ws.prta.file}")
	private String prta_file;
	
	@Value("${dsa.ws.prtt.file}")
	private String prtt_file;
	
	@Value("${server.port}")
	private String client_port;
	
	@Cacheable // Caches the result of method
	public ClientConfig getClientConfig() {
		ClientConfig config = new ClientConfig();
		config.setHost("htttp://123.123.123.123/host/endpoint");
		config.setUsername("login");
		config.setPassword("pass");

		return config;
	}

	public String getPrta() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(prta_url);
		
		String input = new String(Files.readAllBytes(
				ResourceUtils.getFile("classpath:"+prta_file).toPath()));
		log.info("PRTA Test Request : "+input);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("REMOTE_TOKEN", "ZHNwdG9kc2F1YXQ=");
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		
		return toPrettyPrintJson(result.getBody());
	}

	public String getPrtt() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(prtt_url);
		
		String input = new String(Files.readAllBytes(
				ResourceUtils.getFile("classpath:"+prtt_file).toPath()));
		log.info("PRTT Test Request : "+input);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("REMOTE_TOKEN", "ZHNwdG9kc2F1YXQ=");
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		
		return toPrettyPrintJson(result.getBody());
	}
	
	private String toPrettyPrintJson(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(jsonString).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);
		
		return prettyJson;
	}
}
