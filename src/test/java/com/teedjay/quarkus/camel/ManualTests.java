package com.teedjay.quarkus.camel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ManualTests {

    public static void main(String[] args) throws IOException {

        System.out.println("Printing input using peek");
        List<String> list = Files.lines(Paths.get("pom.xml")).peek(System.out::println).map(l -> l.trim()).collect(Collectors.toList());

        System.out.println("Printing output using foreach");
        list.stream().forEach(l -> System.out.println(l));

    }

}
