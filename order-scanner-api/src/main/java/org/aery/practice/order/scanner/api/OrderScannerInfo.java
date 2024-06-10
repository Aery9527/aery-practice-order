package org.aery.practice.order.scanner.api;

import org.aery.practice.order.utils.ComponentInfo;

public record OrderScannerInfo(
        String name
) implements ComponentInfo {
}
