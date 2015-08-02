package com.iostream.internetobserver.internet_observer;

public interface OnInternetStateChangeListener {
    /**
     * -1 - no connection
     * 0 - connected
     * @param wifiOrMobile
     */
    void onInternetStateChange(int wifiOrMobile);
}
