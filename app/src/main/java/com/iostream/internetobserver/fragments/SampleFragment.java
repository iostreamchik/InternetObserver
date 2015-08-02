package com.iostream.internetobserver.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iostream.internetobserver.R;
import com.iostream.internetobserver.broadcasts.InternetReceiver;

public class SampleFragment extends BaseFragment {

	public static final String TAG = "SampleFragment";

	private TextView tvInetState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sample_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tvInetState = (TextView) view.findViewById(R.id.tv_inet_state);
	}

	@Override
	public void onInternetStateChange(int state) {
		super.onInternetStateChange(state);
		switch (state) {
			case InternetReceiver.STATUS_OFFLINE:
				Log.d(TAG, "STATUS_OFFLINE");
				tvInetState.setText("OFFLINE");
				tvInetState.setTextColor(getResources().getColor(android.R.color.holo_red_light));
				break;
			case InternetReceiver.STATUS_ONLINE:
				Log.d(TAG, "STATUS_ONLINE");
				tvInetState.setText("ONLINE");
				tvInetState.setTextColor(getResources().getColor(android.R.color.holo_green_light));
				break;
		}
	}
}
