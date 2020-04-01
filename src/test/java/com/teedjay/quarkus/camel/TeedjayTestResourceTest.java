package com.teedjay.quarkus.camel;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(TeedjayTestResource.class)
public class TeedjayTestResourceTest {

    public String teedjay = "should de over-injected by test resource";
    public String teedjay_again = "all String fields starting with name teedjay are overridden";
    public StringBuffer teedjay_other = new StringBuffer("this will not be overridden");
    public String fixed = "only String fields staring with name teedjay are overridden";

    @Test
    void checkThatSftpServerHasStarted() {
        assertEquals("rules the world", teedjay);
        assertEquals("rules the world", teedjay_again);
        assertEquals("this will not be overridden", teedjay_other.toString());
        assertEquals("only String fields staring with name teedjay are overridden", fixed);
    }

}
