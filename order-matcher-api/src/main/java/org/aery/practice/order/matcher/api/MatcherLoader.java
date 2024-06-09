package org.aery.practice.order.matcher.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface MatcherLoader extends ComponentLoader<Matcher, MatcherInfo> {

    static MatcherLoader create() {
        ServiceLoader<MatcherLoader> serviceLoader = ServiceLoader.load(MatcherLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
