package com.teedjay.quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.UUID;

@QuarkusTest
public class DemoRouteTest {

    @Inject
    ProducerTemplate producer;

    @Inject
    CamelContext context;

    @Test
    public void testDirectEndpoint() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        MockEndpoint result = context.getEndpoint("mock:debug", MockEndpoint.class);
        result.expectedMessageCount(5);
        result.expectedHeaderValuesReceivedInAnyOrder("CamelFileLastModified", Arrays.asList(uuid, "1578425982849", "1578425408803", "1578425408776", "1578425407033"));
        producer.sendBodyAndHeader("direct:debug", "this is my body", "CamelFileLastModified", uuid);
        result.assertIsSatisfied();
    }

}
