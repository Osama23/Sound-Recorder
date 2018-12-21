package com.osama.osama.soundrecoder.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.astuetz.pagerslidingtabstrip.BuildConfig;
import com.osama.osama.soundrecoder.R;
import com.osama.osama.soundrecoder.SettingsActivity;
import com.osama.osama.soundrecoder.db.MySharedPreferences;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class LicensesFragment extends PreferenceFragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference highQualityPref = (CheckBoxPreference) findPreference(getResources()
                .getString(R.string.pref_high_quality_key));
        highQualityPref.setChecked(MySharedPreferences.getPrefHighQuality(getActivity()));
        highQualityPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                MySharedPreferences.setPrefHighQuality(getActivity(), (boolean) newValue);
                return true;
            }
        });

        Preference aboutPref = findPreference(getString(R.string.pref_about_key));
        aboutPref.setSummary(getString(R.string.pref_about_desc, BuildConfig.VERSION_NAME));
        aboutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                LicensesFragment licensesFragment = new LicensesFragment();
                licensesFragment.show(((SettingsActivity)getActivity()).
                                getSupportFragmentManager().beginTransaction(),"dialog_licenses");
                return true;
            }
        });
    }

    public void show(FragmentTransaction fragmentTransaction, String dialog_licenses) {
    }
}
