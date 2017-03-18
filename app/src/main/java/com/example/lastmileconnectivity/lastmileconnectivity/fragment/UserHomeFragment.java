package com.example.lastmileconnectivity.lastmileconnectivity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastmileconnectivity.lastmileconnectivity.R;

public class UserHomeFragment extends Fragment {

    public UserHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_home, container, false);

        return rootView;
    }

}