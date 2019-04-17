package my.com.etiqa.dsawsclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.com.etiqa.dsawsclient.model.Endpoint;

@Controller
@RequestMapping(value = "/ui")
public class ClientUiController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${springboot.property.application.version}")
	private String version;
	
	@Value("${server.port}")
	private String client_port;
	
	@GetMapping("/")
	public String index(Model model) {
		log.info("App version is "+version);
		model.addAttribute("page", "DSA RESTful Web Service Client");
		model.addAttribute("port",client_port);
		
		return "index";
	}
}
