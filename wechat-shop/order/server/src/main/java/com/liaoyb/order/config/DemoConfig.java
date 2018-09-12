package com.liaoyb.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 前缀方式配置,使用@RefreshScope刷新
 *
 * @author liaoyb
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo")
@RefreshScope
public class DemoConfig implements Serializable{
    private static final long serialVersionUID = -8031814806422625344L;
    private String name;
    private Integer count;
}
