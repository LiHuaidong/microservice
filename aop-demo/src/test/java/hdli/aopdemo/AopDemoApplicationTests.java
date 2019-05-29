package hdli.aopdemo;

import hdli.service.impl.AspectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopDemoApplicationTests {

    private Logger logger = LoggerFactory.getLogger(AopDemoApplicationTests.class);

    @Autowired
    AspectService aspectService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        aspectService.sayHello("hdli168@163.com");
        logger.info("Li Huaidong");
    }

}
