package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

// COMPLETED (4) Create SettingsFragment and extend PreferenceFragmentCompat
public class SettingsFragment extends PreferenceFragmentCompat{

    // COMPLETED (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);

    }

}
