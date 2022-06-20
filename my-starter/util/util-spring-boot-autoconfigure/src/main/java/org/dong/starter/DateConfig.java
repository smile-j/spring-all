package org.dong.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UtilProperties.class)
public class DateConfig {

    @Bean
    public DateUtil getDateUtil(){
        System.out.println("-=-=-=-=-=-=-=");
        return new DateUtil();
    }
}
