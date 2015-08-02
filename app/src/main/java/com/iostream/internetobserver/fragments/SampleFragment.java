package com.iostream.internetobserver.fragments;

import android.util.Log;
import com.iostream.internetobserver.broadcasts.InternetReceiver;

public class SampleFragment extends BaseFragment {

	public static final String TAG = "SampleFragment";

	@Override
	public void onInternetStateChange(int state) {
		super.onInternetStateChange(state);
		switch (state) {
			case InternetReceiver.STATUS_OFFLINE:
				Log.d(TAG, "STATUS_OFFLINE");
				break;
			case InternetReceiver.STATUS_ONLINE:
				Log.d(TAG, "STATUS_ONLINE");
				break;
		}
	}
}
