package com.mesbro.standardbroker.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRouter extends RouteBuilder {

	@Autowired
	CamelContext ctx = new DefaultCamelContext();

	@Override
	public void configure() throws Exception {
		rest().id("teste").get("/").to("direct:lol");
		from("direct:lol").setBody(constant("AEEEE PORA TNC"));
	}

}
