package com.fredhappyface.brainf

import android.os.Bundle

/**
 * Define about activity. This is very basic (holds text only)
 */
class ActivityAbout : ActivityThemable() {
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
