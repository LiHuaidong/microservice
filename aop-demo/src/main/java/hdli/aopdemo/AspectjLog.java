package hdli.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class AspectjLog {

    private Logger logger = LoggerFactory.getLogger(AspectjLog.class);

    @Pointcut("execution(* hdli.service.AspectService.*(..))")
    public void logAop(){}

    public void logBefore(JoinPoint joinPoint, String email) {
        logger.debug("前置通知Before>>{}", email);
    }

    @AfterReturning("logAop() && args(email)")
    public void logAfterReturning(String email) {}

}
