package org.bgschaefer.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.bgschaefer.processors.JsonProcessor;

@ApplicationScoped
public class MainRoute extends EndpointRouteBuilder{

    private final String externalHostUri = "https://api.openweathermap.org/data/2.5/weather?q=Blumenau,br&APPID=d24a73177b072e4cbc2e316e20acb866"; 

    @Override
    public void configure() throws Exception {
        rest().consumes("application/json")
        .get("/jsontojson")
        .bindingMode(RestBindingMode.auto)
        .to("direct:forwardRequest");

        from("direct:forwardRequest").routeId("forwardRequest")
        .removeHeader("CamelHttpUri")
        .to(externalHostUri)
        .convertBodyTo(String.class)
        // .unmarshal().json()
        .to("direct:processBody");

        from("direct:processBody")
        .process(new JsonProcessor());
        
    }
    
}
