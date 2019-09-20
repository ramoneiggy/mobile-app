package com.isolaja.mobileapp.security;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    private Environment env;

    public AppProperties(Environment env) {
        this.env = env;
    }

    public String getTokenSecret() {
        return env.getProperty("tokensecret");
    }
}
