package com.example.part3_9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences preferences;

    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);

        soundPreference = (ListPreference)findPreference("sound_list");
        keywordSoundPreference = (ListPreference)findPreference("keyword_sound_list");
        keywordScreen = (PreferenceScreen)findPreference("keyword_screen");

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!preferences.getString("sound_list", "").equals("")){
            soundPreference.setSummary(preferences.getString("sound_list", "카톡"));
        }

        if(!preferences.getString("keyword_sound_list", "").equals("")){
            keywordSoundPreference.setSummary(preferences.getString("keyword_sound_list", "카톡"));
        }

        if(preferences.getBoolean("keyword", false)){
            keywordScreen.setSummary("사용");
        }

        preferences.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    if(s.equals("sound_list")){
                        soundPreference.setSummary(preferences.getString("sound_list", "카톡"));
                    }
                    if(s.equals("keyword_sound_list")){
                        keywordSoundPreference.setSummary(preferences.getString("keyword_sound_list", "카톡"));
                    }
                }
            };
}
