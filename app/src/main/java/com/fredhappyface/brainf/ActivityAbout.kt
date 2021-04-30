package com.fredhappyface.brainf

import com.fredhappyface.brainf.ActivityAbstract
import android.os.Bundle
import com.fredhappyface.brainf.R

/**
 * Define about activity. This is very basic (holds text only)
 */
class ActivityAbout : ActivityAbstract() {
    /**
     * Boilerplate onCreate method. Applies the layout to the activity
     *
     * @param savedInstanceState activity saved data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}