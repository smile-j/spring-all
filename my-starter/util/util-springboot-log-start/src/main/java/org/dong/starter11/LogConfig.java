package org.dong.starter11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/16
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogConfig {

    @Autowired
    private LogProperties logProperties;

   public void test(){
       System.out.println("日志级别："+logProperties.getName());
   }


}
