package org.dong.starter.testcontroller;

import lombok.extern.slf4j.Slf4j;
import org.dong.starter11.LogConfig;
import org.dong.starter.DateUtil;
import org.dong.starter.Main;
import org.dong.starter.producter.controller.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dongjunpeng
 * @Description
 * @date 2022/2/8
 */
@SpringBootTest(classes = {Main.class})
@Slf4j
@RunWith(SpringRunner.class)
public class JunitTestController {

    @Autowired
    private TestController testController;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private LogConfig logConfig;

    @Test
    public void testAutoConfigureDate(){
//        log.info(testController.testUtil());
        log.info("========"+dateUtil.getLocalTime());
    }

    @Test
    public void testStartLog(){
        logConfig.test();

    }
}
