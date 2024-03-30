package com.fredhappyface.brainf

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*

private const val MAX_FILE_SIZE = 1024 * 1024 // 1Mb

/**
 * ActivityMain class inherits from the AppCompatActivity class - provides the settings view
 */
class ActivityMain : AppCompatActivity() {
	/**
	 * Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode
	 * (custom request code); _readRequestCode (request code for reading a file)
	 */
	private var uri: String? = null
	private lateinit var codeEditText: EditText

	/**
	 * Override the onCreate method from AppCompatActivity adding the activity_main view and configuring
	 * the this.codeEditText, the textHighlight and the initial text
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		// Get saved state
		this.uri = savedInstanceState?.getString("_uri", null)
		// Set up correct colour
		var colours: Colours = ColoursDark()
		val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
		if (currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
			colours = ColoursLight()
		}

		// Set up code edit, apply highlighting and some startup text
		this.codeEditText = findViewById(R.id.codeHighlight)
		val textHighlight = TextHighlight(
			this.codeEditText,
			LanguageRules(),
			colours
		)
		textHighlight.start()
		this.codeEditText.setText(R.string.blank_file_text)
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
		outState.putString("_uri", this.uri)
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
			this.codeEditText.setText(R.string.blank_file_text)
			this.uri = null
			recreate()
		}
		alertDialog.show()
	}

	/**
	 * Call this when the user clicks menu -> save
	 *
	 */
	private fun doFileSave() {
		if (this.uri != null) {
			writeTextToUri(Uri.parse(this.uri ?: return))
			showDialogMessageSave()
		} else {
			startFileSaveAs()
		}
	}

	/**
	 * Handles ACTION_OPEN_DOCUMENT result and sets this.uri, mLanguageID and mCodeEditText
	 */
	private val completeFileOpen =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				this.uri = result.data?.data.toString()
				this.codeEditText.setText(readTextFromUri(Uri.parse(this.uri)))
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
	 * Handles ACTION_CREATE_DOCUMENT result and sets this.uri, mLanguageID and triggers writeTextToUri
	 */
	private val completeFileSaveAs =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				this.uri = result.data?.data.toString()
				writeTextToUri(Uri.parse(this.uri))
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
					val bytes = this.codeEditText.text.toString()
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
		contentResolver.query(uri, null, null, null, null).use { cursor ->
			val sizeIdx = cursor?.getColumnIndex(OpenableColumns.SIZE)
			cursor?.moveToFirst()
			val size = sizeIdx?.let { cursor.getLong(it) }
			if ((size ?: 0) > MAX_FILE_SIZE) {
				return "File too large! (over $MAX_FILE_SIZE bytes)\n"
			}
		}
		val inputStream: InputStream? = contentResolver.openInputStream(uri)
		val reader = BufferedReader(InputStreamReader(inputStream))
		return reader.readLines().joinToString("\n")
	}

	/**
	 * Run the interpreter
	 *
	 * @param ignoreView: View? required to call function from layout
	 */
	fun run(ignoreView: View) {
		val brainfInterpreter = BrainfInterpreter(
			this.codeEditText.text.toString(),
			findViewById<EditText>(R.id.stdin).text.toString()
		)
		try {
			var (output, buffer) = brainfInterpreter.execute()
			findViewById<TextView>(R.id.stdout).text = output
			buffer = buffer.copyOfRange(0, 1000)
			val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
			recyclerView.layoutManager = GridLayoutManager(
				this,
				4,
				RecyclerView.HORIZONTAL,
				false,
			)
			recyclerView.adapter = BufferAdapter(buffer)
		} catch (exception: IllegalStateException) {
			val error = "ERROR: " + (exception.message ?: "Unknown")
			findViewById<TextView>(R.id.stdout).text = error
		}
	}
}
