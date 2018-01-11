package com.example.mithr.preferencias;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Mithr on 08/01/2018.
 */

public class Preferencias extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
