package org.aery.practice.order.utils.locate.api;

public interface LocateNode {

    int INITIAL_LOCATE = 0;

    int OFFLINE_LOCATE = -1;

    static boolean isInitial(LocateNode node) {
        return node.getLocate() == INITIAL_LOCATE;
    }

    static boolean isOffline(LocateNode node) {
        return node.getLocate() == OFFLINE_LOCATE;
    }

    String getNodeId();

    default boolean isOffline() {
        return !isOnline();
    }

    boolean isOnline();

    int getLocate();

    default boolean isFirstNode() {
        return isOnlineAndLocateWhen(1);
    }

    default boolean isSecondNode() {
        return isOnlineAndLocateWhen(2);
    }

    default boolean isThirdNode() {
        return isOnlineAndLocateWhen(3);
    }

    boolean isOnlineAndLocateWhen(int locate);

}
