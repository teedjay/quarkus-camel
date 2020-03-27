package com.teedjay.quarkus.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostgresDebeziumRoute extends RouteBuilder {

    @ConfigProperty(name = "debezium.start")
    String debezium;

    @ConfigProperty(name = "debezium.end")
    String theend;

    @Override
    public void configure() throws Exception {
        from(debezium)
            .bean(this, "peek")
            .to(theend);
    }

    void peek(Exchange ex) {
        String data = ex.getIn().getBody(String.class);
        System.out.printf("peeeking the %s%n", data);
    }

}
