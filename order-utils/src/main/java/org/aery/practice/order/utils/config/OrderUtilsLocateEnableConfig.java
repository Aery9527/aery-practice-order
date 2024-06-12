package org.aery.practice.order.utils.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "org.aery.practice.order.utils.locate.impl",
        "org.aery.practice.order.utils.locate.tool"
})
public class OrderUtilsLocateEnableConfig {
}
