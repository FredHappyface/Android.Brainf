package com.fredhappyface.brainf;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import android.view.View;
import com.google.android.material.card.MaterialCardView;


public class ActivitySettings extends ActivityAbstract {

    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final LinearLayout themeChoices = findViewById(R.id.theme);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getInt("theme", 3);

        final MaterialCardView cardView = (MaterialCardView) themeChoices.getChildAt(currentTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardView.setStrokeWidth((int) (3 * getApplicationContext().getResources().getDisplayMetrics().density));
            cardView.setStrokeColor(getResources().getColor(R.color.red, getTheme()));
        }

    }

    public final void changeTheme(@NonNull final View view) {
        int idx = 3;
        switch (view.getId()) {
            case R.id.radioLight:
                idx = 0;
                break;
            case R.id.radioDark:
                idx = 1;
                break;
            case R.id.radioBlack:
                idx = 2;
                break;
        }
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", idx);
        editor.apply();
        recreate();
    }

}
