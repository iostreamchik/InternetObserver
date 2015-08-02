package com.iostream.internetobserver.fragments;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.iostream.internetobserver.broadcasts.InternetReceiver;
import com.iostream.internetobserver.internet_observer.OnInternetStateChangeListener;
import com.iostream.internetobserver.internet_observer.Subject;

public class BaseFragment extends Fragment implements OnInternetStateChangeListener {

	private static final String TAG = "BaseFragment";

	private Subject internetSubject;
	private InternetReceiver myReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter(InternetReceiver.FILTER);
		myReceiver = new InternetReceiver();
		getActivity().registerReceiver(myReceiver, filter);
		internetSubject = myReceiver;
		internetSubject.registerObserver(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		internetSubject.removeObserver(this);
		getActivity().unregisterReceiver(myReceiver);
	}

	@Override
	public void onInternetStateChange(int state) {
	}
}
