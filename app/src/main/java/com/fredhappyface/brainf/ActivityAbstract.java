package com.fredhappyface.brainf;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

/**
 * Provides theming capabilities for my activity classes. These are applied in the onCreate method. This is also where
 * the title is reset. This is due to an issue with applying a color to the titlebar
 */
public abstract class ActivityAbstract extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int currentTheme;

    /**
     * Triggered when the activity is created, sets the title to BlackC4t with a color of #ABB2BF. Sets the theme to one
     * that the user selected
     * @param savedInstanceState activity saved data
     */
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(HtmlCompat.fromHtml("<font color='#FFFFFF'>Brainf</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getInt("theme", 3);
        switch (currentTheme) {
            case (0):
                setTheme(R.style.LightTheme);
                break;
            case (1):
                setTheme(R.style.DarkTheme);
                break;
            case (2):
                setTheme(R.style.BlackTheme);
                break;
            default:
                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        setTheme(R.style.DarkTheme);
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        setTheme(R.style.LightTheme);
                        break;
                }
                break;

        }

    }

    /**
     * Triggered when an activity is resumed. If the theme differs from the currently active theme, then the activity is
     * recreated
     */
    @Override
    protected final void onResume() {
        super.onResume();
        final int theme = sharedPreferences.getInt("theme", 3);
        if (currentTheme != theme) {
            currentTheme = theme;
            recreate();
        }
    }
}
