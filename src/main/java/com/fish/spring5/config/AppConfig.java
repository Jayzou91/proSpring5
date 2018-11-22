package com.fish.spring5.config;

import com.fish.spring5.constant.SystemConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
@Configuration
@EnableScheduling
@ComponentScan(
        basePackages = {SystemConstants.COMPONENT_SCAN_PACKAGE_NAME},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class AppConfig {
}