package com.fredhappyface.brainf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fredhappyface.brainf.R

/**
 * ActivityAbout class inherits from the AppCompatActivity class - provides the about view
 */
class ActivityAbout : AppCompatActivity() {
	/**
	 * Override the onCreate method from AppCompatActivity adding the activity_about view
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_about)
	}
}
