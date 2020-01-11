package com.teedjay.quarkus.camel;

import org.apache.camel.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DebugBean {

    public void printBody(Exchange ex) {
        System.out.printf("Inside the debug bean the body was : '%s'%n", ex.getIn().getBody(String.class));
    }

    public void printFirstLine(Exchange ex) {
        String body = ex.getIn().getBody(String.class).replaceAll("\n", "").strip();
        if (body == null || body.isEmpty()) body = "[empty body]";
        int max = body.length();
        if (max > 32) max = 32;
        System.out.printf("Start if body : '%s'%n", body.substring(0, max));
    }

    public void printHeaders(Exchange ex) {
        System.out.printf("Inside the debug bean the headers was : '%s'%n", ex.getIn().getBody(String.class));
    }

}
