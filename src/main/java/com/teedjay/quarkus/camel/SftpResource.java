package com.teedjay.quarkus.camel;

import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sftp")
public class SftpResource {

    @Inject
    ProducerTemplate template;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String trigger() {
        String output = template.requestBody("direct:sftp", "a,b\nc,d\n", String.class);
        return output;
    }

}
