package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.InetAddressUtil;

import javax.enterprise.context.ApplicationScoped;
import java.net.UnknownHostException;

@ApplicationScoped
public class HttpRoute extends RouteBuilder {

    @Override
    public void configure() throws UnknownHostException {
        String hostname = InetAddressUtil.getLocalHostName();
        from("platform-http:/camel/input")
            .setBody().constant("Hello from " + hostname);
    }

}
