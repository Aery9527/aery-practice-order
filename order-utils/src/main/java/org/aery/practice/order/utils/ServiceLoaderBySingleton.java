package org.aery.practice.order.utils;

import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.error.GlobalException;

import java.util.Collection;
import java.util.ServiceLoader;

public class ServiceLoaderBySingleton {

    public static <Target> Target load(ServiceLoader<Target> serviceLoader) throws GlobalException {
        long foundInstance = serviceLoader.stream().count();
        if (foundInstance == 0) {
            ErrorCode.INCORRECT_STATE.thrown("cannot find any OrderMatcher instance by ServiceLoader");
        } else if (foundInstance > 1) {
            Collection<String> modules = serviceLoader.stream()
                    .map(ServiceLoader.Provider::get)
                    .map(om -> om.getClass().getModule().getName())
                    .toList();
            ErrorCode.INCORRECT_STATE.thrown("found more than one OrderMatcher instance by ServiceLoader : " + modules);
        }

        return serviceLoader.findFirst().get();
    }

    public static interface ComponentsLoader<Target> {

        Target start();

    }
}
