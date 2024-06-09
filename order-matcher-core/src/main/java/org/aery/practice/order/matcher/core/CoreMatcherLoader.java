package org.aery.practice.order.matcher.core;

import org.aery.practice.order.matcher.api.Matcher;
import org.aery.practice.order.matcher.api.MatcherInfo;
import org.aery.practice.order.matcher.api.MatcherLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class CoreMatcherLoader extends ComponentLoaderAbstract<Matcher, MatcherInfo> implements MatcherLoader {

    public static void main(String[] args) {
        new CoreMatcherLoader().start(args);
    }

    @Override
    public Class<? extends Matcher> getTargetType() {
        return CoreMatcher.class;
    }

}
