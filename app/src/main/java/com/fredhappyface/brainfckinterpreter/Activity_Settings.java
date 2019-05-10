package com.fredhappyface.brainfckinterpreter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


public class Activity_Settings extends Abstract_Activity {

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public final void changeTheme(final View view) {
        final RadioGroup themeChoices = findViewById(R.id.theme);

        final int radioButtonID = themeChoices.getCheckedRadioButtonId();
        final View radioButton = themeChoices.findViewById(radioButtonID);
        final int idx = themeChoices.indexOfChild(radioButton);

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", idx);
        editor.apply();
        recreate();
    }
}
