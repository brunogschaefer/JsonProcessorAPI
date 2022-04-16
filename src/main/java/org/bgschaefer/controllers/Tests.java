package org.bgschaefer.controllers;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tests")
@ApplicationScoped
public class Tests {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "teste";

    }
    
}
