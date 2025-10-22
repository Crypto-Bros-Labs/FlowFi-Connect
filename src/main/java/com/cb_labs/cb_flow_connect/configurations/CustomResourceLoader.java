package com.cb_labs.cb_flow_connect.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class CustomResourceLoader {

    @Autowired
    private ResourceLoader resourceLoader;

    public InputStream getResource(String path) {
        return resourceLoader.getClass().getClassLoader().getResourceAsStream(path);
    }
}
