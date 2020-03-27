package com.teedjay.quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class CsvRouteTest {

    @Inject
    CamelContext camel;

    @Inject
    ProducerTemplate producer;

    @Test
    public void testCsvTest() throws InterruptedException {
        MockEndpoint mock = camel.getEndpoint("mock:outputcsv", MockEndpoint.class);
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("123|hansen|ola\n");
        producer.sendBody("direct:inputcsv", "123|ola|hansen|adresse|3712|skien");
        mock.assertIsSatisfied();
    }

}
