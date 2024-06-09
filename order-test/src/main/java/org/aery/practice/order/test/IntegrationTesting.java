package org.aery.practice.order.test;

import org.aery.practice.order.customer.Customer;
import org.aery.practice.order.customer.CustomerLoader;
import org.aery.practice.order.matcher.api.Matcher;
import org.aery.practice.order.matcher.api.MatcherLoader;
import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.scanner.api.Scanner;
import org.aery.practice.order.scanner.api.ScannerLoader;

public class IntegrationTesting {

    public static void main(String[] args) {
        MatcherLoader matcherLoader = MatcherLoader.create();
        Matcher matcher = matcherLoader.start(args);

        PortalLoader portalLoader = PortalLoader.create();
        Portal portal = portalLoader.start(args);

        ScannerLoader scannerLoader = ScannerLoader.create();
        Scanner scanner = scannerLoader.start(args);

        CustomerLoader customerLoader = CustomerLoader.create();
        Customer customer = customerLoader.start(args);

        customer.simulate(portal);

        System.out.println("Mather   load : " + matcher.getInfo().name());
        System.out.println("portal   load : " + portal.getInfo().name());
        System.out.println("scanner  load : " + scanner.getInfo().name());
        System.out.println("customer load : " + customer.getInfo().name());

        matcherLoader.stop();
        portalLoader.stop();
        scannerLoader.stop();
        customerLoader.stop();
    }

}
