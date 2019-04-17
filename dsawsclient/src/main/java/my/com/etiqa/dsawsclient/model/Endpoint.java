package my.com.etiqa.dsawsclient.model;

import lombok.Data;

@Data
public class Endpoint {
	
	private String url;
	private String name;
	
	public Endpoint(String url, String name) {
		this.url = url;
		this.name = name;
	}
	
}
