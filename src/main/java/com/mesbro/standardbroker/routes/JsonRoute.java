package com.mesbro.standardbroker.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class JsonRoute extends RouteBuilder{

    private static String externalHostUri = "https://api.openweathermap.org/data/2.5/weather?q=Blumenau,br&APPID=d24a73177b072e4cbc2e316e20acb866";

    @Override
    public void configure() throws Exception {
        rest().consumes("application/json")
        .post("/jsontojson")
        .bindingMode(RestBindingMode.auto)
        .to("direct:postJsontoJson");

        from("direct:postJsontoJson").routeId("getJsonFromExternal")
        .removeHeader("CamelHttpUri")
        .to(externalHostUri)
        .unmarshal().json()
        .to("jolt:processBody")
        .log("teste + ${body}");
    }
}


