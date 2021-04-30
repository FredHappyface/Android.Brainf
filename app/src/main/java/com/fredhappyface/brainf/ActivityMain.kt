package com.fredhappyface.brainf

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
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

/*
Developed by Kieran W on the 01/02/2019
*/
/*
 * The aim of this android app is to parse a file
 * and to produce an interpreter for the 'Brainfuck' programming language
 */
class ActivityMain : ActivityAbstract() {
    /*
    Create the activity and apply the activity_main view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
    Create the overflow menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /*
    What to do when an option has been selected
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        /*
        For the about field
         */if (itemId == R.id.action_about) {
            startActivity(Intent(this, ActivityAbout::class.java))
            return true
        }
        if (itemId == R.id.action_settings) {
            startActivity(Intent(this, ActivitySettings::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private var fileContent: String? = null

    /**
     * Fires an intent to spin up the "file chooser" UI and select an file.
     */
    fun performFileSearch(view: View?) {
        // Open a file with the system's file browser
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        // Filter to only show results that can be "opened"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        // Start this activity in the hope of a result
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    /**
     * This is run (hopefully) when a file has been selected
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {

        /* Check if the file was picked successfully and that this result is from the expected activity
         */
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            // Check that there is result data and get the URI
            if (resultData != null) {
                val uri = resultData.data
                var uriString = ""
                // Attempt to convert the URI to a string
                if (uri != null) {
                    uriString = uri.toString()
                }
                // Attempt to read the data from the file
                try {
                    fileContent = readTextFromUri(uri)
                } catch (e: Exception) {
                    val error = String.format(ERR_FILE_IS_INVALID, uriString)
                    reportError(error)
                }
                if (fileContent == null || fileContent!!.isEmpty()) {
                    val error = String.format(ERR_FILE_IS_NULL, uriString)
                    reportError(error)
                }
                // Populate the textview with the file contents
                val output = findViewById<TextView>(R.id.fileContents)
                output.text = fileContent
            }
        }
    }

    /*
    Report and error with a toast notification, and log it to the console
     */
    private fun reportError(error: String) {
        // System.out.println(error);
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    /*
    Read the file text from the URI
     */
    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri?): String {
        val inputStream = contentResolver.openInputStream(uri!!)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        // Read the next line from the file and add it to the output string
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

    /*
    Run the interpreter
     */
    fun run(view: View) {
        // Check that the file has been loaded first
        if (fileContent == null) {
            reportError(ERR_FILE_NOT_LOADED)
        } else {
            brainfuckInterpreter(syntaxCleaner(fileContent!!))
        }
    }

    /*
     * The purpose of this function is to strip non brainfuck syntax, this is to
     * make the program more modular. The syntax is "< > + - . , [ ]"
     */
    private fun syntaxCleaner(fileContents: String): String {
        // Define variables
        val fileContentsLen = fileContents.length
        val legalChars = charArrayOf('<', '>', '+', '-', '.', ',', '[', ']')
        val cleanSyntax = StringBuilder()
        // For every character in the input string...
        for (syntaxPointer in 0 until fileContentsLen) {
            // ...Select the char at the position of the pointer
            val element = fileContents[syntaxPointer]
            // ...And compare it to each char in the legalChars array
            for (legalChar in legalChars) {
                if (element == legalChar) {
                    // Add the char to the output if it is part of the syntax
                    cleanSyntax.append(element)
                }
            }
        }
        return cleanSyntax.toString()
    }

    /*
     * The purpose of this function is to take the cleaned syntax and execute
     * the appropriate function based on this
     */
    private fun brainfuckInterpreter(instruction: String) {
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
                    val error = String.format(locale, ERR_POINTER_LT_ZERO,
                            instructionPointer, currentInstruction)
                    reportError(error)
                    return
                }
            }

            // Define > operator
            if (currentInstruction == '>') {
                if (arrayPointer < MAX_SIZE) {
                    arrayPointer++
                } else {
                    val error = String.format(locale, ERR_POINTER_GT_MAX, MAX_SIZE,
                            instructionPointer, currentInstruction)
                    reportError(error)
                    return
                }
            }

            // Define - operator
            if (currentInstruction == '-') {
                if (value > Int.MIN_VALUE) {
                    array[arrayPointer]--
                } else {
                    val error = String.format(locale, ERR_VALUE_LT_MIN,
                            instructionPointer, currentInstruction, arrayPointer)
                    reportError(error)
                    return
                }
            }

            // Define + operator
            if (currentInstruction == '+') {
                if (value < Int.MAX_VALUE) {
                    array[arrayPointer]++
                } else {
                    val error = String.format(locale, ERR_VALUE_GT_MAX,
                            instructionPointer, currentInstruction, arrayPointer)
                    reportError(error)
                    return
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
                if (!inputText.isEmpty()) {
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
                        reportError(ERR_INPUT_INVALID)
                        return
                    }
                    inputCounter++
                } else {
                    reportError(ERR_INPUT_REQUIRED)
                    return
                }

                // Terminate if input is called too many times
                if (inputCounter >= MAX_INPUT) {
                    val error = String.format(locale, ERR_EXCEEDED_INPUT,
                            instructionPointer, currentInstruction, arrayPointer, MAX_INPUT)
                    reportError(error)
                    return
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
        val success = INFO_EXECUTION_COMPLETE
        reportError(success)
        outputBuffer.append(success)

        // Populate the textview with the string
        val output = findViewById<TextView>(R.id.output)
        output.text = outputBuffer.toString()
    }

    companion object {
        // Errors
        private const val ERR_FILE_NOT_LOADED = "ERROR: Load a file before running"
        private const val ERR_FILE_IS_INVALID = "ERROR: The specified file " +
                "does not exist (filename: %s)"
        private const val ERR_FILE_IS_NULL = "ERROR: The specified file does " +
                "not contain any text (filename: %s)"
        private const val ERR_POINTER_LT_ZERO = "ERROR: The pointer cannot be " +
                "less than zero (instruction: %d, %s)"
        private const val ERR_POINTER_GT_MAX = ("ERROR: The pointer cannot be "
                + "greater than the size of the array (array_size: %d instruction: %d, %s)")
        private const val ERR_VALUE_LT_MIN = ("ERROR: The value cannot be "
                + "less than the size of the minimum integer (instruction: %d, %s " +
                "pointer: %d)")
        private const val ERR_VALUE_GT_MAX = ("ERROR: The value cannot be "
                + "greater than the size of the maximum integer (instruction: %d, %s " +
                "pointer: %d)")
        private const val ERR_EXCEEDED_INPUT = "ERROR: This program requests " +
                "too much input from the user (instruction: %d, %s pointer: %d limit: %d)"
        private const val ERR_INPUT_REQUIRED = "ERROR: Input is required here"
        private const val ERR_INPUT_INVALID = "ERROR: Input is too short or in the incorrect format: " +
                "must be a comma separated list or a string of characters"

        // Information
        private const val INFO_EXECUTION_COMPLETE = "INFO: Code executed " +
                "successfully"

        // Constants
        private const val MAX_SIZE = 30000
        private const val MAX_INPUT = 20
        private val locale = Locale.ENGLISH
        private const val READ_REQUEST_CODE = 42
    }
}