package com.liaoyb.order.controller;

import com.liaoyb.order.config.DemoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoyb
 */
@RestController
@RefreshScope
public class ConfigDemoController {
    @Value("${env}")
    private String env;

    @Autowired
    private DemoConfig demoConfig;

    @GetMapping("/env")
    public String env() {
        return env;
    }

    @GetMapping("/demo")
    public String demo() {
        return "name:"+demoConfig.getName() + "count:"+demoConfig.getCount();
    }
}
