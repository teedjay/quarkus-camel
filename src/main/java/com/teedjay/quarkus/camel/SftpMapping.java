package com.teedjay.quarkus.camel;

public class SftpMapping {

    public String map(String input) {
        return new StringBuffer(input).reverse().toString();
    }

}
