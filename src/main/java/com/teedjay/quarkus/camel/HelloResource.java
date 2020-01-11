package com.teedjay.quarkus.camel;

import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @ConfigProperty(name = "greetings.hello")
    String hello;

    @Inject
    ProducerTemplate template;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String databaseTime = template.requestBody("direct:TriggerMe", "select now()", String.class);
        return hello + ", keep up the good work, time in database was " + databaseTime;
    }

}
