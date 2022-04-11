package com.mesbro.standardbroker.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest().id("teste").get("/").to("direct:lol");
		from("direct:lol").setBody(constant("AEEEE PORA TNC"));
	}

}
