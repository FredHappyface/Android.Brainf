package com.fredhappyface.brainf

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.noties.markwon.Markwon

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

		val markwon: Markwon = Markwon.create(this)
		val featuresText = findViewById<TextView>(R.id.features_text)
		markwon.setMarkdown(featuresText, getString(R.string.features_text))

		val linksText = findViewById<TextView>(R.id.links_text)
		markwon.setMarkdown(linksText, getString(R.string.links_text))

		val versionText: TextView = findViewById(R.id.versionText)
		try {
			val packageInfo = packageManager.getPackageInfo(packageName, 0)
			versionText.text = packageInfo.versionName
		} catch (e: PackageManager.NameNotFoundException) {
			versionText.text = getString(R.string.version_text)
		}
	}
}
