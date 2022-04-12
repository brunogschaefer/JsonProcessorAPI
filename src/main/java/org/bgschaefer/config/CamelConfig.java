package org.bgschaefer.config;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class CamelConfig extends RouteBuilder{

    // @Value("${server.port}")
    private final String serverPort = "8080";

    // @Value("${main.api.path}")
    private final String contextPath = "localhost";
    
	CamelContext ctx = new DefaultCamelContext();

    @Override
    public void configure() throws Exception {
        restConfiguration()
        .contextPath(contextPath)
        .port(serverPort)
        .enableCORS(true)
        // .apiContextPath("/api-doc")
        // .apiProperty("api.title", "Standard Camel RESTAPI")
        // .apiProperty("api.version", "v1")
        // .apiContextRouteId("")
        .component("servlet")
        .bindingMode(RestBindingMode.json);
        
    }
    
}
