package my.com.etiqa.dsawsclient.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Response {

	private List<Endpoint> endpoint = new ArrayList<Endpoint>();
	
}
