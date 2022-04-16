package com.broker.routes;

import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.FailedToCreateRouteException;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import com.broker.processors.JsonProcessor;
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
        .route().routeId("connector")
        .toD("direct:forwardRequest");

        from("direct:forwardRequest")
        .routeId("forward-request") 
        .removeHeader("CamelHttpUri")
        .doTry()
            .toD("platform-http:" + externalHostUri)
            .convertBodyTo(String.class)
            .to("direct:processBody")
        .doCatch(UnknownHostException.class)
            .log("Falha ao criar a rota com o servidor. Favor, checar se a URL fornecida está correta")
        .endDoTry();

        from("direct:processBody")
        .routeId("processor")
        .process(new JsonProcessor()).endRest();
    }
}

//ResolveEndpointFailedException
//FailedToCreateRouteException
