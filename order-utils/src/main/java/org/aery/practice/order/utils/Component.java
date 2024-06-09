package org.aery.practice.order.utils;

public interface Component<InfoType extends ComponentInfo> {

    InfoType getInfo();

}
