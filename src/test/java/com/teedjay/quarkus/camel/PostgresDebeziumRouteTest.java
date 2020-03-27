package com.teedjay.quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.CamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PostgresDebeziumRouteTest {

    @Inject
    CamelContext camel;

    @Test
    public void testCsvTest() throws InterruptedException {
        MockEndpoint mock = camel.getEndpoint("mock:debezium", MockEndpoint.class);
        mock.expectedMessageCount(3);
        mock.assertIsSatisfied();
        String bodyOfThirdExchange = mock.getExchanges().get(2).getIn().getBody(String.class);
        assertEquals("Struct{id=3,username=jola,config={}}", bodyOfThirdExchange);
    }

}
