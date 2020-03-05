package com.teedjay.quarkus.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class DemoRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("timer:foo?period=10s").id("CrazyRouteID")
            .loop(4)
                .pollEnrich().simple("file:///Users/thore/Downloads/quarkus-camel/target/classes?recursive=true&noop=true").timeout(1000)
                .choice()
                    .when(body().isNull())
                        .removeHeaders("*")
                        .process(new MyProcessor())
                        .to("log:body-was-empty?showExchangePattern=true&showBodyType=true")
                    .otherwise()
                        .to("direct:debug")
                .end()
            .end()
        ;

        from("file-watch:///Users/thore/Downloads/quarkus-camel/target/target")
            .bean(this, "debug")
        ;

        from("direct:debug")
            .bean(this, "debug")
            .to("mock:debug")
        ;

        from("timer:test?period=1s&delay=10s").id("route-to-shutdown")
            .bean(this, "debug")
            .to("seda:shutdown")
            .bean(this, "noop")
            .log("log:end-timer")
        ;

        from("seda:shutdown")
            .to("controlbus:route?routeId=route-to-shutdown&action=stop")
        ;

    }

    void empty(Exchange ex) {
        System.out.println("empty");
    }

    void noop(Exchange ex) {
        System.out.println("noop");
    }

    void debug(Exchange ex) {
        ex.getIn().getHeaders().entrySet().forEach(h -> System.out.println(h));
    }

}
