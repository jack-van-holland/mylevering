package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFrag extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("Settings");
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        MainActivity main = (MainActivity) getActivity();
//        if (main != null) {
//            main.setTitle("Settings");
//        }
//        return inflater.inflate(R.layout.fragment_settings, container, false);
//    }
}