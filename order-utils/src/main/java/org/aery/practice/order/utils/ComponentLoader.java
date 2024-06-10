package org.aery.practice.order.utils;

import org.aery.practice.order.utils.error.GlobalException;

import java.util.Arrays;
import java.util.stream.Stream;

public interface ComponentLoader<ComponentType extends Component<InfoType>, InfoType extends ComponentInfo> {

    static Class<?>[] mergeSource(ComponentLoader<?, ?>... loaders) {
        return Arrays.stream(loaders)
                .flatMap(loader -> Stream.concat(Stream.of(loader.getCoreType()), Arrays.stream(loader.getOtherSources())))
                .distinct()
                .toArray(Class[]::new);
    }

    ComponentType start(String... args) throws GlobalException;

    void stop() throws GlobalException;

    Class<? extends ComponentType> getCoreType();

    /**
     * 設計這個主要是可以讓該模組要額外載入其他spring模組,
     * 而其他spring模組並沒有採用 autoconfig 的話,
     * 就可以從這裡提供 config 從而跟該模組於同個 spring context 載入.
     */
    default Class<?>[] getOtherSources() {
        return new Class<?>[0];
    }

}
