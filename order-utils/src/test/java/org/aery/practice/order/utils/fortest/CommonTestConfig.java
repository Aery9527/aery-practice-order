package org.aery.practice.order.utils.fortest;

import org.aery.practice.order.utils.config.auto.BaseAutoConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles("test")
@Import({
        BaseAutoConfig.class
})
public @interface CommonTestConfig {
}
