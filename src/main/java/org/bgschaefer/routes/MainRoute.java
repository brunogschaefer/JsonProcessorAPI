package org.bgschaefer.routes;

import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.bgschaefer.processors.JsonProcessor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MainRoute extends EndpointRouteBuilder{

    @ConfigProperty(name = "api.consumes.external")
    private String externalHostUri; 

    @ConfigProperty(name = "api.rest.get")
    private String getPath;

    @Override
    public void configure() throws Exception {
        rest().consumes("application/json")
        .get(getPath)
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
