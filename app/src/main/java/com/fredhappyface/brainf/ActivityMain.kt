package com.fredhappyface.brainf

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * The aim of this android app is to parse a file
 * and to produce an interpreter for the 'brainf' programming language
 */
class ActivityMain : ActivityThemable() {
	/**
	 * Storage of private vars. These being _fileContent
	 */
	private var mFileContent: String? = null
	private val constMaxSize = 16384 // 16k (2^14)

	/**
	 * Override the onCreate method from ActivityThemable adding the activity_main view
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	/**
	 * Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted
	 * menu on the title bar)
	 *
	 * @param menu Menu - this is the popup menu (containing a series of actions)
	 * @return Boolean - success!
	 */
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	/**
	 * Override the onOptionsItemSelected method. This is essentially a callback method triggered when
	 * the end user selects a menu item. Here we filter the item/ action selection and trigger a
	 * corresponding action. E.g. action_open -> startFileOpen()
	 *
	 * @param item MenuItem - this is the item/ action that the user taps
	 * @return Boolean - success!
	 */
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.action_about -> {
				startActivity(Intent(this, ActivityAbout::class.java)); true
			}
			R.id.action_settings -> {
				startActivity(Intent(this, ActivitySettings::class.java)); true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	/**
	 * Call this when the user clicks open button
	 *
	 * @param view: View? required to call function from layout
	 */
	fun startFileOpen(view: View?) {
		intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
		intent.addCategory(Intent.CATEGORY_OPENABLE)
		intent.type = "*/*"
		completeFileOpen.launch(intent)
	}

	/**
	 * Handles ACTION_OPEN_DOCUMENT result and populates R.id.fileContents with the
	 * contents of the selected file
	 */
	private val completeFileOpen =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				val uri = (result.data ?: return@registerForActivityResult).data.toString()
				val text = readTextFromUri(Uri.parse(uri))
				mFileContent =
					text.substring(0, text.length.coerceAtMost(constMaxSize)) // Limit to 16kb
				findViewById<TextView>(R.id.fileContents).text = mFileContent
			}
		}

	/*
	Report and error with a toast notification, and log it to the console
	 */
	private fun reportError(error: String) {
		val err = "ERROR: $error"
		findViewById<TextView>(R.id.output).text = err
		Toast.makeText(applicationContext, err, Toast.LENGTH_LONG).show()
	}

	/**
	 * Read the file text from the URI
	 *
	 * @param uri Uri - the uri of the file we are going to read
	 * @return String - contents of the file (decoded per readLines())
	 */
	private fun readTextFromUri(uri: Uri): String {
		val inputStream: InputStream? = contentResolver.openInputStream(uri)
		val reader = BufferedReader(InputStreamReader(inputStream))
		return reader.readLines().joinToString("\n")
	}

	/**
	 * Run the interpreter
	 *
	 * @param view: View? required to call function from layout
	 */
	fun run(view: View) {
		// Check that the file has been loaded first
		if (mFileContent == null) {
			reportError("File Not Loaded")
		} else {
			val brainfInterpreter = BrainfInterpreter(
				mFileContent ?: "",
				findViewById<EditText>(R.id.input_text_edit).text.toString()
			)
			try {
				findViewById<TextView>(R.id.output).text = brainfInterpreter.execute()
			} catch (exception: IllegalStateException) {
				reportError(exception.message ?: "Unknown")
			}


		}
	}

}
