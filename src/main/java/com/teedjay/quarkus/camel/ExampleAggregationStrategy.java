package com.teedjay.quarkus.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class ExampleAggregationStrategy implements AggregationStrategy {

    private int counter = 0;

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (newExchange == null) System.out.println("new exchange null");
        if (oldExchange == null) System.out.println("old exchange null");

        Exchange ex = (oldExchange == null) ? newExchange : oldExchange;

        if (newExchange == null) {
            if (counter > 0) {
                ex.getIn().setHeader(Exchange.AGGREGATION_COMPLETE_ALL_GROUPS_INCLUSIVE, "true");
                System.out.println("Aggregate is finished with " + counter + " items");
                counter = 0;
            }
            return ex;
        }

        counter++;
        ex.getIn().setHeader("file_" + counter, newExchange.getExchangeId());
        if (counter == 3) {
            ex.getIn().setHeader(Exchange.AGGREGATION_COMPLETE_ALL_GROUPS_INCLUSIVE, "true");
            System.out.println("Aggregate is finished with " + counter  + " items");
            counter = 0;
        }
        return ex;
    }

}
