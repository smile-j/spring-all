package org.dong.starter;


import lombok.extern.slf4j.Slf4j;
import org.dong.starter11.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

@SpringBootApplication
@Slf4j
//@EnableScheduling
//@MapperScan
//@EnableFeignClients
/**
 * starter
 *
 * @Import：starter的jar包任意路径
 * spring.factories ：必须和当前项目扫描路径一样
 */
//@Import(DateConfig.class)
@Import(LogConfig.class)
public class Main {

    public static void main(String[] args) {
//        SpringApplication.run(Main.class,args);
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
            ApplicationContext context = builder.web(WebApplicationType.SERVLET).run(args);
            String[] activeProfiles = context.getEnvironment().getActiveProfiles();
            String port = context.getEnvironment().getProperty("server.port");
            log.info("ActiveProfiles = {},ServerPort = {}", StringUtils.arrayToCommaDelimitedString(activeProfiles), port);
        } catch (Exception e) {
            log.error("异常信息{}", e);
        }
    }

}
