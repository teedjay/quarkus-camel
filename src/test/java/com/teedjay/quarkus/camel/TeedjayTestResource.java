package com.teedjay.quarkus.camel;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class TeedjayTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        // return system properties that shoud be set for the running test
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
    }

    @Override
    public void inject(Object testInstance) {
        // all public String fields with names staring with teedjay will have text injected
        Arrays.stream(testInstance.getClass().getFields())
            .filter(field -> field.getType().equals(String.class))
            .filter(field -> field.getName().startsWith("teedjay"))
            .forEach(field -> yay(testInstance, field));
    }

    private void yay(Object testInstance, Field field) {
        try {
            field.set(testInstance, new String("rules the world"));
        } catch (Exception e) {
            throw new RuntimeException("failed to set field", e);
        }
    }

    @Override
    public int order() {
        // optional
        return 0;
    }

}
