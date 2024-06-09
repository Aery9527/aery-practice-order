package org.aery.practice.order.matcher.core;

import org.aery.practice.order.matcher.api.Matcher;
import org.aery.practice.order.matcher.api.MatcherInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreMatcher implements Matcher {

    private final MatcherInfo info = new MatcherInfo("core-matcher");

    @Override
    public MatcherInfo getInfo() {
        return this.info;
    }

}
