package com.fredhappyface.brainfckinterpreter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


public class Settings extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void changeTheme(View view) {
        RadioGroup themeChoices = findViewById(R.id.theme);

        int radioButtonID = themeChoices.getCheckedRadioButtonId();
        View radioButton = themeChoices.findViewById(radioButtonID);
        int idx = themeChoices.indexOfChild(radioButton);

        System.out.println("qwerty:" + idx);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", idx);
        editor.apply();
        recreate();
    }
}
