package com.mesbro.standardbroker.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelConfig extends RouteBuilder{
    @Value("${server.port}")
    private String serverPort;

    @Value("${main.api.path}")
    private String contextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
        .contextPath(contextPath)
        .port(serverPort)
        .enableCORS(true)
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "Standard Camel RESTAPI")
        .apiProperty("api.version", "v1")
        .apiContextRouteId("doc-api")
        .component("servlet")
        .bindingMode(RestBindingMode.json);
        
    }


    

}
