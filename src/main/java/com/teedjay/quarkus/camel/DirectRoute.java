package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DirectRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:TriggerMe")
            .to("jdbc:postgres")
            .to("bean:debugBean?method=printBody")
        ;
    }

}
