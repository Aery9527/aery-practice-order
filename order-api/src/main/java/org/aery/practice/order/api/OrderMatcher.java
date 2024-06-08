package org.aery.practice.order.api;

import org.aery.practice.order.api.data.OrderMatcherInfo;
import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.error.GlobalException;

import java.util.Collection;
import java.util.ServiceLoader;

public interface OrderMatcher {

    static OrderMatcher load() {
        ServiceLoader<OrderMatcher> serviceLoader = ServiceLoader.load(OrderMatcher.class);

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


    default void start(String... args) throws GlobalException {
        ErrorCode.UNSUPPORT_ACTION.thrown();
    }

    default void stop() throws GlobalException {
        ErrorCode.UNSUPPORT_ACTION.thrown();
    }

    OrderMatcherInfo getInfo();

}
