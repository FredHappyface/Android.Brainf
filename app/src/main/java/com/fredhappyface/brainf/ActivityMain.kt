package com.fredhappyface.brainf

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
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
	private var _fileContent: String? = null

	/**
	 *
	 */
	companion object {
		private const val MAX_SIZE = 16384 // 16k (2^14)
		private const val MAX_INPUT = 32
		private const val READ_REQUEST_CODE = 42
	}


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
		startActivityForResult(intent, READ_REQUEST_CODE)

	}


	/**
	 * This is basically a callback from an activity
	 *
	 * @param requestCode Int - RequestCode as defined under the Activity private vars
	 * @param resultCode Int - The result code, we only want to do stuff if successful
	 * @param data Intent? - Extra data in the form of an intent. tend to access .data
	 */
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == READ_REQUEST_CODE) {
				completeFileOpen(data)
			}
		}
	}


	/**
	 * Callback from onActivityResult
	 *
	 * @param data Intent - some intent with a uri (accessed with .data)
	 */
	private fun completeFileOpen(data: Intent?) {
		val uri = (data ?: return).data.toString()
		val output = findViewById<TextView>(R.id.fileContents)
		val text = readTextFromUri(Uri.parse(uri))
		_fileContent = text.substring(0, text.length.coerceAtMost(MAX_SIZE)) // Limit to 16kb
		output.text = _fileContent
	}

	/*
	Report and error with a toast notification, and log it to the console
	 */
	private fun reportError(error: String) {
		// System.out.println(error);
		Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()

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
		if (_fileContent == null) {
			reportError(getString(R.string.err_file_not_loaded))
		} else {
			brainfInterpreter(_fileContent ?: return)
		}
	}

	/*
	 * The purpose of this function is to take the cleaned syntax and execute
	 * the appropriate function based on this
	 */
	private fun brainfInterpreter(instruction: String) {
		// Define variables
		val array = IntArray(MAX_SIZE)
		var arrayPointer = 0
		var instructionPointer = 0
		val instructionLen = instruction.length
		var inputCounter = 0
		val outputBuffer = StringBuilder()

		// Get the mode
		val modeRad = findViewById<RadioButton>(R.id.modeAscii)
		val isAsciiMode = modeRad.isChecked

		// While still reading instructions
		while (instructionPointer < instructionLen) {
			var currentInstruction = instruction[instructionPointer]
			val value = array[arrayPointer]

			// Define < operator
			if (currentInstruction == '<') {
				if (arrayPointer != 0) {
					arrayPointer--
				} else {
					return reportError(String.format(getString(R.string.err_pointer_lt_zero),
							instructionPointer, currentInstruction))
				}
			}

			// Define > operator
			if (currentInstruction == '>') {
				if (arrayPointer < MAX_SIZE) {
					arrayPointer++
				} else {
					return reportError(String.format(getString(R.string.err_pointer_gt_max), MAX_SIZE,
							instructionPointer, currentInstruction))

				}
			}

			// Define - operator
			if (currentInstruction == '-') {
				if (value > Int.MIN_VALUE) {
					array[arrayPointer]--
				} else {
					return reportError(String.format(getString(R.string.err_value_lt_min),
							instructionPointer, currentInstruction, arrayPointer))
				}
			}

			// Define + operator
			if (currentInstruction == '+') {
				if (value < Int.MAX_VALUE) {
					array[arrayPointer]++
				} else {
					return reportError(String.format(getString(R.string.err_value_gt_max),
							instructionPointer, currentInstruction, arrayPointer))

				}
			}

			// Define . operator
			if (currentInstruction == '.') {
				if (isAsciiMode) {
					outputBuffer.append(value.toChar())
				} else {
					outputBuffer.append(value)
					outputBuffer.append(", ")
				}
			}

			// Define , operator
			if (currentInstruction == ',') {
				// Get the input
				val input = findViewById<EditText>(R.id.input_text_edit)
				var inputText = input.text.toString()
				// Flag for invalid input
				var invalidInput = false
				if (inputText.isNotEmpty()) {
					if (isAsciiMode) {
						try {
							array[arrayPointer] = inputText[inputCounter].toInt()
						} catch (e: Exception) {
							invalidInput = true
						}
					} else {
						inputText = inputText.replace("\\s".toRegex(), "")
						if (inputText.contains(",")) {
							val intParts = inputText.split(",").toTypedArray()
							try {
								array[arrayPointer] = intParts[inputCounter].toInt()
							} catch (e: Exception) {
								invalidInput = true
							}
						}
						try {
							array[arrayPointer] = inputText.toInt()
						} catch (e: Exception) {
							invalidInput = true
						}
					}
					if (invalidInput) {
						reportError(getString(R.string.err_input_invalid))
						return
					}
					inputCounter++
				} else {
					reportError(getString(R.string.err_input_required))
					return
				}

				// Terminate if input is called too many times
				if (inputCounter >= MAX_INPUT) {
					return reportError(String.format(getString(R.string.err_exceeded_input),
							instructionPointer, currentInstruction, arrayPointer, MAX_INPUT))

				}
			}

			// Define [ operator
			// Need to find the matching closing bracket
			if (currentInstruction == '[') {
				if (value == 0) {
					var brackets = 0
					while (true) {
						// Decrement the pointer and refresh the current instruction
						instructionPointer++
						currentInstruction = instruction[instructionPointer]
						// Another opening bracket is encountered
						if (currentInstruction == '[') {
							brackets++
						} else if (currentInstruction == ']') {
							// If this is the matching bracket
							if (brackets == 0) {
								break
							} else {
								brackets--
							}
						}
					}
				}
			}

			// Define ] operator
			// Need to find the matching opening bracket
			if (currentInstruction == ']') {
				if (value > 0) {
					var brackets = 0
					while (true) {
						// Decrement the pointer and refresh the current instruction
						instructionPointer--
						currentInstruction = instruction[instructionPointer]
						// Another closing bracket is encountered
						if (currentInstruction == ']') {
							brackets++
						} else if (currentInstruction == '[') {
							// If this is the matching bracket
							if (brackets == 0) {
								break
							} else {
								brackets--
							}
						}
					}
				}
			}

			// Increment the instruction
			instructionPointer++
		}

		// Inform the user that code execution is complete
		val success = getString(R.string.info_exe_complete)
		reportError(success)
		outputBuffer.append(success)

		// Populate the textview with the string
		val output = findViewById<TextView>(R.id.output)
		output.text = outputBuffer.toString()
	}


}
