package org.bgschaefer.routes;

import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.bgschaefer.processors.JsonProcessor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MainRoute extends EndpointRouteBuilder{

    @ConfigProperty(name = "jsonprocessor.consumes.host")
    private String externalHostUri; 

    @Override
    public void configure() throws Exception {
        rest().consumes("application/json")
        .get("/jsontojson")
        .bindingMode(RestBindingMode.auto)
        .to("direct:forwardRequest");

        from("direct:forwardRequest")
        .routeId("forwardRequest") // tratar exceção
        .removeHeader("CamelHttpUri")
        .to(externalHostUri)
        .convertBodyTo(String.class)
        .to("direct:processBody");

        from("direct:processBody")
        .process(new JsonProcessor()).endRest();
    }
    
}
