package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;

public class SftpRoute extends RouteBuilder {

    // simple sftp example
    // TODO add test resource that starts https://github.com/atmoz/sftp using testcontainers

    @Override
    public void configure() throws Exception {
        from("direct:sftp")
            .bean(SftpMapping.class)
            .to("mock:sftp");
    }

}
