package hse.kpo.interfaces.sales;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for sale methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sales {

    /**
     * name of operation.
     *
     * @return name of operation.
     */
    String value() default "";
};
