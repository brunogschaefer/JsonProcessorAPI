package com.mesbro.standardbroker.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class JsonRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        rest().consumes("application/json")
        .post("/jsontojson")
        .bindingMode(RestBindingMode.auto)
        .to("direct:postJsontoJson");

        from("direct:postJsontoJson")
        .log("${body}")
        .setBody(simple("${body}"));
    }
    
}
