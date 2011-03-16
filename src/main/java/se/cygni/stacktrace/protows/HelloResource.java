package se.cygni.stacktrace.protows;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {
	
	@GET
	@Produces("text/plain")
	public String sayHello() {
		return "Hello, world!";
	}
}
