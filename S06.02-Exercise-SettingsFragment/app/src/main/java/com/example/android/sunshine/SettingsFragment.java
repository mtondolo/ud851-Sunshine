package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

// COMPLETED (4) Create SettingsFragment and extend PreferenceFragmentCompat
// COMPLETED (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    // COMPLETED (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);

        // COMPLETED (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            // You don't need to set up preference summaries for checkbox preferences because
            // they are already set up in xml using summaryOff and summary On
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }

    }

    // COMPLETED (8) Create a method called setPreferenceSummary that accepts a Preference and an Object
    // and sets the summary of the preference
    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();
        String key = preference.getKey();

        if (preference instanceof ListPreference) {

            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            } else  {
                preference.setSummary(stringValue);
            }
        }
    }

    // COMPLETED (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Figure out which preference was changed
        Preference preference = findPreference(key);
        if (null != preference) {
            // Updates the summary for the preference
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }
    }

    // COMPLETED (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
    }

