package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostgresRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://SelectDataEveryHour?period=36000")
            //.setBody(constant("select * from information_schema.tables"))
            .setBody(constant("select * from quarkus.message"))
            .to("jdbc:postgres")
            .to("log:postgres?showExchangePattern=false&showBodyType=false");
    }

}
