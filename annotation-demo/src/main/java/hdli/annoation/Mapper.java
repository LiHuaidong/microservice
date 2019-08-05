package hdli.annoation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:25 2019/7/26
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapper {

	String value() default "";

}
