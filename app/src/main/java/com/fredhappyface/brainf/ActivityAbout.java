package com.fredhappyface.brainf;

import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * Define about activity. This is very basic (holds text only)
 */
public class ActivityAbout extends ActivityAbstract {

    /**
     * Boilerplate onCreate method. Applies the layout to the activity
     *
     * @param savedInstanceState activity saved data
     */
    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
