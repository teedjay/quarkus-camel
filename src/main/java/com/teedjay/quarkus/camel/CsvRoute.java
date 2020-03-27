package com.teedjay.quarkus.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CsvRoute extends RouteBuilder {

    @ConfigProperty(name = "teedjay.csv.destination", defaultValue = "direct:sendCSV")
    String destination;

    @Override
    public void configure() throws Exception {

        CsvDataFormat csvDataFormat = new CsvDataFormat();
        csvDataFormat.setDelimiter("|");
        csvDataFormat.setRecordSeparator("\n");

        from("direct:inputcsv")
            .unmarshal(csvDataFormat)
            .bean(this, "convertFormat")
            .marshal(csvDataFormat)
            .to(destination);

    }

    List<List<String>> convertFormat(List<List<String>> csv) {
        return csv.stream().map(this::convertLine).collect(Collectors.toList());
    }

    List<String> convertLine(List<String> line) {
        return List.of(line.get(0), line.get(2), line.get(1));
    }

}
