package my.com.etiqa.dsawsclient.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.com.etiqa.dsawsclient.model.ClientConfig;
import my.com.etiqa.dsawsclient.model.Endpoint;
import my.com.etiqa.dsawsclient.model.Response;
import my.com.etiqa.dsawsclient.service.DsawsclientService;

@RestController
@RequestMapping(value = "/endpoint")
public class ClientEndpointsController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private DsawsclientService service;
	
	@Value("${server.port}")
	private String client_port;
	
	@GetMapping(value = "/config")
	public ClientConfig getAllConfig() {
		return service.getClientConfig();
	}
	
	@GetMapping(value = "/options")
	public Response getOptions() throws URISyntaxException {
		log.info("Retrieving Options");
		Response resp = new Response();
		resp.getEndpoint().add(new Endpoint("http://localhost:"+client_port+"/endpoint/prta","PRTA"));
		resp.getEndpoint().add(new Endpoint("http://localhost:"+client_port+"/endpoint/prtt","PRTT"));
		
		return resp;
	}
	
	@GetMapping(value = "/prta")
	public String getPrta() throws URISyntaxException, IOException {
		log.info("Sending request to Prta");
		return service.getPrta();
	}

	@GetMapping(value = "/prtt")
	public String getPrtt() throws URISyntaxException, IOException {
		log.info("Sending request to Prtt");
		return service.getPrtt();
	}
}
