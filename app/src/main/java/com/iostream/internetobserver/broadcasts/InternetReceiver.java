package com.iostream.internetobserver.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.iostream.internetobserver.internet_observer.OnInternetStateChangeListener;
import com.iostream.internetobserver.internet_observer.Subject;

import java.util.ArrayList;

public class InternetReceiver extends BroadcastReceiver implements Subject {

	private static final String TAG = "InternetReceiver";

	public static final String FILTER = ConnectivityManager.CONNECTIVITY_ACTION;

	public static final int STATUS_OFFLINE = 0;
	public static final int STATUS_ONLINE = 1;

	private ArrayList observers;

	public InternetReceiver() {
		super();
		observers = new ArrayList();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		//should check null because in air plan mode it will be null
		boolean isConnected = netInfo != null && netInfo.isConnected();

		/*NEW REALIZATION*/
//		try {
//			Process proc = Runtime.getRuntime().exec(new String[]{
//					"/system/bin/ping", "-W", "30", "-c", "1", "8.8.8.8"});
//			if (proc.waitFor() == 0) {
//				onInternetStateChange(STATUS_ONLINE);
//			} else {
//				onInternetStateChange(STATUS_OFFLINE);
//			}
//		} catch (InterruptedException | IOException e) {
//			Log.e(TAG, "Ping failed", e);
//			onInternetStateChange(STATUS_OFFLINE);
//		}

		/*OLD REALIZATION*/
		if (isConnected) {
			Log.i(TAG, "found connection");
			onInternetStateChange(STATUS_ONLINE);
		} else {
			Log.i(TAG, "lost connection");
			onInternetStateChange(STATUS_OFFLINE);
		}
	}

	@Override
	public void registerObserver(OnInternetStateChangeListener o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(OnInternetStateChangeListener o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers(int state) {
		for (Object observer1 : observers) {
			OnInternetStateChangeListener observerInternet = (OnInternetStateChangeListener) observer1;
			observerInternet.onInternetStateChange(state);
		}
	}

	private void onInternetStateChange(int state) {
		notifyObservers(state);
	}
}