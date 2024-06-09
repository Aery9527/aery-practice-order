package org.aery.practice.order.service;

import org.aery.practice.order.matcher.api.Matcher;
import org.aery.practice.order.matcher.api.MatcherLoader;
import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.scanner.api.Scanner;
import org.aery.practice.order.scanner.api.ScannerLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Service {

    public static void main(String[] args) {
        MatcherLoader matcherLoader = MatcherLoader.create();
        PortalLoader portalLoader = PortalLoader.create();
        ScannerLoader scannerLoader = ScannerLoader.create();

        Class<? extends Matcher> matcherType = matcherLoader.getTargetType();
        Class<? extends Portal> portalType = portalLoader.getTargetType();
        Class<? extends Scanner> scannerType = scannerLoader.getTargetType();

        Class<?>[] types = new Class<?>[]{matcherType, portalType, scannerType};
        ConfigurableApplicationContext sprintContext = SpringApplication.run(types, args);
        Matcher matcher = sprintContext.getBean(matcherType);
        Portal portal = sprintContext.getBean(portalType);
        Scanner scanner = sprintContext.getBean(scannerType);

        System.out.println("Mather  load : " + matcher.getInfo().name());
        System.out.println("portal  load : " + portal.getInfo().name());
        System.out.println("scanner load : " + scanner.getInfo().name());

        sprintContext.stop();
    }

}
