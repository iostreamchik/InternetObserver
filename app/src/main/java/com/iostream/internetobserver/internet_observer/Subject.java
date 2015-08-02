package com.iostream.internetobserver.internet_observer;

public interface Subject {
    void registerObserver(OnInternetStateChangeListener o);
    void removeObserver(OnInternetStateChangeListener o);
    void notifyObservers(int state);
}
