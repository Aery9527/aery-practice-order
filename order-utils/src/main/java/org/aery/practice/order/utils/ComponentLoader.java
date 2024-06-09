package org.aery.practice.order.utils;

import org.aery.practice.order.utils.error.GlobalException;

public interface ComponentLoader<ComponentType extends Component<InfoType>, InfoType extends ComponentInfo> {

    ComponentType start(String... args) throws GlobalException;

    void stop() throws GlobalException;

    Class<? extends ComponentType> getTargetType();

}
