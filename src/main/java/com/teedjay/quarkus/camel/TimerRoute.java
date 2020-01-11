package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TimerRoute extends RouteBuilder {

    @ConfigProperty(name = "timer.period", defaultValue = "9s")
    String period;

    @Override
    public void configure() throws Exception {

        errorHandler(deadLetterChannel("log:error"));

        fromF("timer:foo?period=%s", period)
            .setBody(() -> "Incremented the counter: " + System.currentTimeMillis())
            .to("log:timer?showExchangePattern=false&showBodyType=false");

    }

}
