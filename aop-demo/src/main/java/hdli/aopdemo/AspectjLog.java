package hdli.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class AspectjLog implements Runnable {

    private Logger logger = LoggerFactory.getLogger(AspectjLog.class);

    public static int abc = 123;

    public static final int abc_constant = 123;

    @Pointcut("execution(* hdli.service.impl.AspectService.*(..))")
    public void logAop(){
        logger.debug("logAop {}", 123);
    }

    @Before("logAop() && args(email)")
    public void logBefore(JoinPoint joinPoint, String email) {
        logger.debug("前置通知Before>>{}", email);
    }

    @AfterReturning("logAop() && args(email)")
    public void logAfterReturning(String email) {
        logger.debug("返回通知AfterReturning>>{}", email);
    }

    @After("logAop() && args(email)")
    public void logAfter(String email) {
        logger.info("后置通知After>>{}", email);
    }

    @AfterThrowing("logAop() && args(email)")
    public void logAfterThrow(String email) throws RuntimeException {
        logger.info("异常通知AfterThrowing>>{}", email);
    }

    //环绕通知功能很强,可以替代上面的所有通知
    @Around("logAop() && args(email)")
    public void logAround(ProceedingJoinPoint jp, String email) {
        try {
            logger.info("自定义前置通知Before>>>{}", email);
            jp.proceed();//将控制权交给被通知的方法，也就是执行sayHello方法
            logger.info("自定义返回通知AfterReturning>>>{}", email);
        } catch (Throwable throwable) {
            logger.info("异常处理>>>>{}", email);
            throwable.printStackTrace();
        } finally {
            logger.info("test finally");
        }
        logger.info("自定义后置通知After>>>{}", email);
    }

    @Override
    public void run() {
        System.out.println(12);
    }
}
