package hdli.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "hdli")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RootConfig {
}
