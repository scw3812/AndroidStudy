package com.example.part3_9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;


public class PracticePreferenceFragment extends PreferenceFragment {
    SharedPreferences preferences;

    ListPreference networkPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.practice_preference);

        networkPreference = (ListPreference)findPreference("network_list");

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!preferences.getString("network_list","").equals("")){
            networkPreference.setSummary(preferences.getString("network_list", "LTE(권장)"));
        }

        preferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    if(s.equals("network_list")){
                        networkPreference.setSummary(preferences.getString("network_list", "LTE(권장)"));
                    }
                }
            };
}
