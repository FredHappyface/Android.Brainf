package com.fredhappyface.brainfckinterpreter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
Develeloped by Kieran W on the 01/02/2019
*/
/*
 * The aim of this android app is to parse a file
 * and to produce an interpreter for the 'Brainfuck' programming language
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String fileContent = null;

    // Errors
    final static String ERR_FILE_IS_INVALID = "ERROR: The specified file " +
            "does not exist (filename: %s)";
    final static String ERR_FILE_IS_NULL = "ERROR: The specified file does " +
            "not contain any text (filename: %s)";
    final static String ERR_POINTER_LT_ZERO = "ERROR: The pointer cannot be " +
            "less than zero (instruction: %d, %s)";
    final static String ERR_POINTER_GT_MAX = "ERROR: The pointer cannot be "
            + "greater than the size of the array (array_size: %d instruction: %d, %s)";
    final static String ERR_VALUE_LT_MIN = "ERROR: The value cannot be "
            + "less than the size of the minimum integer (instruction: %d, %s " +
            "pointer: %d)";
    final static String ERR_VALUE_GT_MAX = "ERROR: The value cannot be "
            + "greater than the size of the maximum integer (instruction: %d, %s " +
            "pointer: %d)";
    final static String ERR_EXCEEDED_INPUT = "ERROR: This program requests " +
            "too much input from the user (instruction: %d, %s pointer: %d limit: %d)";

    // Warnings
    final static String WARN_FILE_MAY_BE_INVALID = "WARN: The specified file "
            + "may not be a 'Brainf*ck' file as it does not have the extention .b or " +
            ".bf (filename: %s)";
    final static String WARN_HIGH_INPUT = "WARN: This program is requesting " +
            "too much input from the user (instruction: %d, %s pointer: %d limit: %d)";

    // Information
    final static String INFO_EXECUTION_COMPLETE = "INFO: Code executed " +
            "successfully";

    final static String INPUT_MSG = "INFO: Input requested (type: %s) ";

    // Constants
    final static int MAX_SIZE = 30000;
    final static int MAX_INPUT = 20;
    public enum MODE{
        ASCII, INT
    };

    private static final int READ_REQUEST_CODE = 42;

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performFileSearch(View v) {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);


        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    fileContent = readTextFromUri(uri);
                }
                catch(Exception e){
                    String error = String.format(ERR_FILE_IS_INVALID, uri.toString());
                    System.out.println(error);
                    Toast.makeText(getApplicationContext(), error,
                            Toast.LENGTH_LONG).show();
                }
                if(fileContent == null || fileContent.length() == 0){
                    String error = String.format(ERR_FILE_IS_NULL, uri.toString());
                    System.out.println(error);
                    Toast.makeText(getApplicationContext(), error,
                            Toast.LENGTH_LONG).show();
                }
                // Populate the textview with the string
                TextView output = findViewById(R.id.fileContents);
                output.setText(fileContent);



            }
        }
    }


    private String readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }


    public void run(View v){

        brainfuckInterpreter(syntaxCleaner(fileContent), MODE.ASCII);

    }


    /*
     * The purpose of this function is to strip non brainfuck syntax, this is to
     * make the program more modular. The syntax is "< > + - . , [ ]"
     */
    public String syntaxCleaner(String fileContents) {
        // Define variables
        int fileContentsLen = fileContents.length();
        char[] legalChars = { '<', '>', '+', '-', '.', ',', '[', ']' };
        int legalCharsLen = legalChars.length;
        StringBuffer cleanSyntax = new StringBuffer();
        // For every character in the input string...
        for (int syntaxPointer = 0; syntaxPointer < fileContentsLen;
             syntaxPointer++) {
            // ...Select the char at the position of the pointer
            char element = fileContents.charAt(syntaxPointer);
            // ...And compare it to each char in the legalChars array
            for (int legalPointer = 0; legalPointer < legalCharsLen;
                 legalPointer++) {
                if (element == legalChars[legalPointer]) {
                    // Add the char to the output if it is part of the syntax
                    cleanSyntax.append(element);
                }
            }
        }
        return cleanSyntax.toString();
    }

    /*
     * The purpose of this function is to take the cleaned syntax and execute
     * the appropriate function based on this
     */
    public  void brainfuckInterpreter(String instruction, MODE mode) {
        // Define variables
        int[] array = new int[MAX_SIZE];
        int arrayPointer = 0;
        int instructionPointer = 0;
        int instructionLen = instruction.length();
        int inputCounter = 0;

        // While still reading instructions
        while (instructionPointer < instructionLen) {
            char currentInstruction = instruction.charAt(instructionPointer);
            int value = array[arrayPointer];

            // Define < operator
            if (currentInstruction == '<') {
                if (arrayPointer != 0) {
                    arrayPointer--;
                } else {
                    System.out.println(String.format(ERR_POINTER_LT_ZERO,
                            instructionPointer, currentInstruction));
                    return;
                }
            }

            // Define > operator
            if (currentInstruction == '>') {
                if (arrayPointer < MAX_SIZE) {
                    arrayPointer++;
                } else {
                    System.out.println(
                            String.format(ERR_POINTER_GT_MAX, MAX_SIZE,
                                    instructionPointer, currentInstruction));
                    return;
                }
            }

            // Define - operator
            if (currentInstruction == '-') {
                if (value > Integer.MIN_VALUE) {
                    array[arrayPointer]--;
                } else {
                    System.out.println(String.format(ERR_VALUE_LT_MIN,
                            instructionPointer, currentInstruction, arrayPointer));
                    return;
                }
            }

            // Define + operator
            if (currentInstruction == '+') {
                if (value < Integer.MAX_VALUE) {
                    array[arrayPointer]++;
                } else {
                    System.out.println(String.format(ERR_VALUE_GT_MAX,
                            instructionPointer, currentInstruction, arrayPointer));
                    return;
                }
            }

            // Define . operator
            if (currentInstruction == '.') {
                switch(mode){
                    case ASCII:{
                        System.out.print((char) value);
                        break;
                    }
                    case INT:{
                        System.out.print(value + ", ");
                        break;
                    }
                }


            }

            // Define , operator
            if (currentInstruction == ',') {

                System.out.print(String.format(INPUT_MSG, mode.toString()));
                /*
                Scanner reader = new Scanner(System.in);
                inputCounter ++;
                switch(mode){
                    case ASCII:{
                        array[arrayPointer] = reader.next().charAt(0);
                        break;
                    }
                    case INT:{
                        array[arrayPointer] = reader.nextInt();
                        break;
                    }

                }
                */

                // Terminate if input is called too many times
                if(inputCounter >= MAX_INPUT * 0.75){
                    System.out.println(String.format(WARN_HIGH_INPUT,
                            instructionPointer, currentInstruction, arrayPointer, MAX_INPUT));
                }
                if(inputCounter >= MAX_INPUT){
                    System.out.println(String.format(ERR_EXCEEDED_INPUT,
                            instructionPointer, currentInstruction, arrayPointer, MAX_INPUT));
                    //reader.close();
                    return;
                }


                //reader.close();
            }

            // Define [ operator

            // Need to find the matching closing bracket
            if (currentInstruction == '[') {
                if (value == 0) {
                    int brackets = 0;
                    while (true) {
                        // Decrement the pointer and refresh the current instruction
                        instructionPointer++;
                        currentInstruction = instruction.charAt(instructionPointer);
                        // Another opening bracket is encountered
                        if (currentInstruction == '[') {
                            brackets++;
                        }
                        // A closing bracket is encountered
                        else if (currentInstruction == ']') {
                            // If this is the matching bracket
                            if (brackets == 0) {
                                break;
                            } else {
                                brackets--;
                            }
                        }
                    }
                }
            }

            // Define ] operator

            // Need to find the matching opening bracket
            if (currentInstruction == ']') {
                if (value > 0) {
                    int brackets = 0;
                    while (true) {
                        // Decrement the pointer and refresh the current instruction
                        instructionPointer--;
                        currentInstruction = instruction.charAt(instructionPointer);
                        // Another closing bracket is encountered
                        if (currentInstruction == ']') {
                            brackets++;
                        }
                        // An opening bracket is encountered
                        else if (currentInstruction == '[') {
                            // If this is the matching bracket
                            if (brackets == 0) {
                                break;
                            } else {
                                brackets--;
                            }
                        }
                    }

                }
            }


            // Increment the instruction
            instructionPointer++;
        }

        // Inform the user that code execution is complete
        System.out.println(INFO_EXECUTION_COMPLETE);

    }





}
