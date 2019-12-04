package com.fredhappyface.brainf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

abstract public class Abstract_Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int currentTheme;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getInt("theme", 0);
        switch (currentTheme) {

            case (1):
                setTheme(R.style.DarkTheme);
                break;
            case (2):
                setTheme(R.style.BlackTheme);
                break;
            default:
                setTheme(R.style.LightTheme);
                break;
        }

    }

    @Override
    final protected void onResume() {
        super.onResume();
        final int theme = sharedPreferences.getInt("theme", 0);
        if (currentTheme != theme) {
            currentTheme = theme;
            recreate();
        }
    }
}
