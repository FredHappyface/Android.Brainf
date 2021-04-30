package com.fredhappyface.brainf

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.preference.PreferenceManager
import com.google.android.material.card.MaterialCardView

/**
 * Settings activity providing the ability to update the theme
 */
class ActivitySettings : ActivityThemable() {

	/**
	 * Override the onCreate method from ActivityThemable adding the activity_settings view and selecting
	 * the current theme
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_settings)
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

		// Theme apply border to selected
		val themeChoices = findViewById<LinearLayout>(R.id.theme)
		val currentTheme = sharedPreferences.getInt("theme", 3)
		val cardView = themeChoices.getChildAt(currentTheme) as MaterialCardView
		cardView.strokeWidth = (3 * applicationContext.resources.displayMetrics.density).toInt()
		cardView.strokeColor = resources.getColor(R.color.red, theme)
	}

	fun changeTheme(view: View) {
		var idx = 3
		when (view.id) {
			R.id.radioLight -> idx = 0
			R.id.radioDark -> idx = 1
			R.id.radioBlack -> idx = 2
		}
		val editor: SharedPreferences.Editor = sharedPreferences.edit()
		editor.putInt("theme", idx)
		editor.apply()
		recreate()
	}


}