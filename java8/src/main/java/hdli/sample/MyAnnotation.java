package hdli.sample;

import java.lang.annotation.*;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:20 2019/2/15
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {

 	String value();

 	int index() default 0;

	String[] params() default {};
}
