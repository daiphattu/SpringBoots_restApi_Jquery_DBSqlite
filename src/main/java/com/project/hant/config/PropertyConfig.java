package com.project.hant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * get property value in file
 */
@PropertySources({
        @PropertySource("classpath:messages.properties")
})
@Configuration
public class PropertyConfig {
}
