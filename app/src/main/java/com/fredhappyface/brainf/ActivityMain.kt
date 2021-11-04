package com.fredhappyface.brainf

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import java.io.*

/**
 * ActivityMain class inherits from the ActivityThemable class - provides the settings view
 */
class ActivityMain : ActivityThemable() {
	/**
	 * Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode
	 * (custom request code); _readRequestCode (request code for reading a file)
	 */
	private var mUri: String? = null
	private lateinit var mCodeEditText: EditText

	/**
	 * Override the onCreate method from ActivityThemable adding the activity_main view and configuring
	 * the mCodeEditText, the textHighlight and the initial text
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		// Get saved state
		mUri = savedInstanceState?.getString("_uri", null)
		// Set up correct colour
		var colours: Colours = ColoursDark()
		if (mCurrentTheme == 0) {
			colours = ColoursLight()
		}
		// Set up code edit, apply highlighting and some startup text
		mCodeEditText = findViewById(R.id.codeHighlight)
		val textHighlight = TextHighlight(
			mCodeEditText,
			LanguageRules(),
			colours
		)
		textHighlight.start()
		mCodeEditText.setText(R.string.blank_file_text)
	}

	/**
	 * Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted
	 * menu on the title bar)
	 *
	 * @param menu Menu - this is the popup menu (containing a series of actions)
	 * @return Boolean - success!
	 */
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		val inflater: MenuInflater = menuInflater
		inflater.inflate(R.menu.main_menu, menu)
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
		// Handle item selection
		return when (item.itemId) {
			R.id.action_new_file -> {
				doNewFile(); true
			}
			R.id.action_open -> {
				startFileOpen(); true
			}
			R.id.action_save -> {
				doFileSave(); true
			}
			R.id.action_save_as -> {
				startFileSaveAs(); true
			}
			R.id.action_settings -> {
				startActivity(Intent(this, ActivitySettings::class.java)); true
			}
			R.id.action_about -> {
				startActivity(Intent(this, ActivityAbout::class.java)); true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	/**
	 * Override onSaveInstanceState to save the _languageID and _uri when recreating the activity
	 *
	 * @param outState: Bundle
	 */
	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putString("_uri", mUri)
	}

	/**
	 * Show a 'saved' dialog. In a function as its reused a couple of times
	 *
	 */
	private fun showDialogMessageSave() {
		val alertDialog: AlertDialog = AlertDialog.Builder(this, R.style.DialogTheme).create()
		alertDialog.setTitle(getString(R.string.dialog_saved_title))
		alertDialog.setButton(
			AlertDialog.BUTTON_NEUTRAL, getString(R.string.dialog_saved_button)
		) { dialog, _ ->
			dialog.dismiss()
			recreate()
		}
		alertDialog.show()
	}

	/**
	 * Call this when the user clicks menu -> new file
	 *
	 */
	private fun doNewFile() {
		val alertDialog: AlertDialog = AlertDialog.Builder(this, R.style.DialogTheme).create()
		alertDialog.setTitle(getString(R.string.dialog_new_title))
		// Cancel/ No - Do nothing
		alertDialog.setButton(
			AlertDialog.BUTTON_NEGATIVE, getString(R.string.dialog_new_cancel)
		) { dialog, _ -> dialog.dismiss(); }
		// Confirm/ Yes - Overwrite text, reset language id and uri and refresh
		alertDialog.setButton(
			AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_new_confirm)
		) { dialog, _ ->
			dialog.dismiss()
			mCodeEditText.setText(R.string.blank_file_text)
			mUri = null
			recreate()
		}
		alertDialog.show()
	}

	/**
	 * Call this when the user clicks menu -> save
	 *
	 */
	private fun doFileSave() {
		if (mUri != null) {
			writeTextToUri(Uri.parse(mUri ?: return))
			showDialogMessageSave()
		} else {
			startFileSaveAs()
		}
	}

	/**
	 * Handles ACTION_OPEN_DOCUMENT result and sets mUri, mLanguageID and mCodeEditText
	 */
	private val completeFileOpen =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				mUri = result.data?.data.toString()
				mCodeEditText.setText(readTextFromUri(Uri.parse(mUri)))
				recreate()
			}
		}

	/**
	 * Call this when the user clicks menu -> open
	 *
	 */
	private fun startFileOpen() {
		val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
		intent.addCategory(Intent.CATEGORY_OPENABLE)
		intent.type = "*/*"
		completeFileOpen.launch(intent)
	}

	/**
	 * Handles ACTION_CREATE_DOCUMENT result and sets mUri, mLanguageID and triggers writeTextToUri
	 */
	private val completeFileSaveAs =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				mUri = result.data?.data.toString()
				writeTextToUri(Uri.parse(mUri))
				showDialogMessageSave()
			}
		}

	/**
	 * Call this when the user clicks menu -> save as
	 *
	 */
	private fun startFileSaveAs() {
		val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
		intent.addCategory(Intent.CATEGORY_OPENABLE)
		intent.type = "*/*"
		completeFileSaveAs.launch(intent)
	}

	/**
	 * Write the file text to the URI
	 *
	 * @param uri Uri - the uri of the file we are going to overwrite
	 * @return Boolean - success/ failure!
	 */
	private fun writeTextToUri(uri: Uri): Boolean {
		try {
			contentResolver.openFileDescriptor(uri, "rwt")?.use { it ->
				FileOutputStream(it.fileDescriptor).use {
					val bytes = mCodeEditText.text.toString()
						.toByteArray()
					it.write(bytes, 0, bytes.size)
				}
			}
			return true
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
		} catch (e: IOException) {
			e.printStackTrace()
		} catch (e: SecurityException) {
			e.printStackTrace()
		}
		return false
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
		val brainfInterpreter = BrainfInterpreter(
			mCodeEditText.text.toString(),
			findViewById<EditText>(R.id.stdin).text.toString()
		)
		try {
			findViewById<TextView>(R.id.stdout).text = brainfInterpreter.execute()
		} catch (exception: IllegalStateException) {
			val error = "ERROR: " + (exception.message ?: "Unknown")
			findViewById<TextView>(R.id.stdout).text = error
		}
	}
}
