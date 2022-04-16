package org.bgschaefer.config;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelConfig extends RouteBuilder{

    @ConfigProperty(name = "api.port")
    private String serverPort;

    @ConfigProperty(name = "api.host")
    private String serverHost;
    
	CamelContext ctx = new DefaultCamelContext();

    @Override
    public void configure() throws Exception {
        restConfiguration()
        .contextPath(serverHost)
        .port(serverPort)
        .enableCORS(true)
        .component("servlet")
        .bindingMode(RestBindingMode.json);
    }
}
