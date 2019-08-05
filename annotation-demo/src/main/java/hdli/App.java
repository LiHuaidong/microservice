package hdli;

import hdli.annoation.Mapper;
import hdli.mapper.NewMapper;

import java.lang.annotation.Annotation;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Annotation[] annotations = NewMapper.class.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation.annotationType().equals(Mapper.class)) {
                System.out.println("annotation = [" + annotation.annotationType() + "]");
            }
        }

    }
}
