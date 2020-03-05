package com.teedjay.quarkus.camel;

import org.apache.camel.Exchange;

public class MyProcessor implements org.apache.camel.Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setBody("this used to be an empty body");
    }

}
