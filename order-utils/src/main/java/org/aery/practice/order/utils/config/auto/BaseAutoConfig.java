package org.aery.practice.order.utils.config.auto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "org.aery.practice.order.utils.bean.impl",
        "org.aery.practice.order.utils.schedule"
})
public class BaseAutoConfig {
}
