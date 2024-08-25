package com.fredhappyface.brainf

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random


class UtilsKtTest {


    @Test
    fun `test hex notation`() {
        assertEquals("\u0000\u0001", parseSpecialChars("\\x00\\x01"))
    }

    @Test
    fun `test control characters`() {
        assertEquals("\u0000\u0001", parseSpecialChars("@NUL;@SOH;"))
    }


    @Test
    fun `test hex notation for unprintable ASCII characters`() {
        assertEquals(
            "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\t\n\u000B\u000C\r\u000E\u000F\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001A\u001B\u001C\u001D\u001E\u001F",
            parseSpecialChars("\\x00\\x01\\x02\\x03\\x04\\x05\\x06\\x07\\x08\\x09\\x0A\\x0B\\x0C\\x0D\\x0E\\x0F\\x10\\x11\\x12\\x13\\x14\\x15\\x16\\x17\\x18\\x19\\x1A\\x1B\\x1C\\x1D\\x1E\\x1F")
        )
    }

    @Test
    fun `test control characters for all ASCII control codes`() {
        assertEquals(
            "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\t\n\u000B\u000C\r\u000E\u000F\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001A\u001B\u001C\u001D\u001E\u001F",
            parseSpecialChars("@NUL;@SOH;@STX;@ETX;@EOT;@ENQ;@ACK;@BEL;@BS;@HT;@LF;@VT;@FF;@CR;@SO;@SI;@DLE;@DC1;@DC2;@DC3;@DC4;@NAK;@SYN;@ETB;@CAN;@EM;@SUB;@ESC;@FS;@GS;@RS;@US;")
        )
    }

    @Test
    fun `test mixed control and hex notation`() {
        assertEquals("\u0000\u0001Hello\u0002World", parseSpecialChars("@NUL;@SOH;Hello\\x02World"))
    }

    @Test
    fun `test escape sequences`() {
        assertEquals(
            "Escaped backslashes: \\ and @ characters: @",
            parseSpecialChars("Escaped backslashes: \\\\ and @ characters: @")
        )
    }

    @Test
    fun `test invalid hex notation`() {
        assertEquals("\\xZZ", parseSpecialChars("\\xZZ"))
    }

    @Test
    fun `test unknown control sequences`() {
        assertEquals("@UNKNOWN", parseSpecialChars("@UNKNOWN"))
    }

    @Test
    fun `test empty input`() {
        assertEquals("", parseSpecialChars(""))
    }

    @Test
    fun `test non-special characters`() {
        assertEquals("Hello World", parseSpecialChars("Hello World"))
    }


    @Test
    fun `test control characters with unknown names`() {
        assertEquals("@FOO;@BAR;", parseSpecialChars("@FOO;@BAR;"))
    }

    @Test
    fun `test malformed input with missing semicolon`() {
        assertEquals("@NUL@BS@foo", parseSpecialChars("@NUL@BS@foo"))
    }

    @Test
    fun `test incomplete hex notation`() {
        assertEquals("\\x0\\x", parseSpecialChars("\\x0\\x"))
    }

    @Test
    fun `test single backslash`() {
        assertEquals("\\", parseSpecialChars("\\"))
    }

    @Test
    fun `test escaped backslashes`() {
        assertEquals("\\", parseSpecialChars("\\\\"))
    }

    @Test
    fun `test escaped hex notation with invalid hex`() {
        assertEquals("\\xZZ", parseSpecialChars("\\xZZ"))
    }

    @Test
    fun `test mixed valid and invalid sequences`() {
        assertEquals("\u0000\\xGG@NUL@foo\u0001", parseSpecialChars("\\x00\\xGG@NUL@foo\\x01"))
    }

    @Test
    fun `test control characters with different cases`() {
        assertEquals("\u0000\u0001\u0008", parseSpecialChars("@nul;@soh;@Bs;"))
    }

    @Test
    fun `test edge case with empty input`() {
        assertEquals("", parseSpecialChars(""))
    }

    @Test
    fun `test edge case with only semicolon`() {
        assertEquals(";", parseSpecialChars(";"))
    }

    @Test
    fun `test control characters with missing control name after @`() {
        assertEquals("@;@;", parseSpecialChars("@;@;"))
    }

    @Test
    fun `test hex notation with various lengths`() {
        assertEquals("\u0000\u0001\u00FF", parseSpecialChars("\\x00\\x01\\xFF"))
    }

    @Test
    fun `test consecutive control codes`() {
        assertEquals("\u0000\u0001\u0002\u0003", parseSpecialChars("@NUL;@SOH;@STX;@ETX;"))
    }


    private fun generateRandomInput(): String {
        val length = Random.nextInt(0, 100) // Random length between 0 and 100
        val characters = (' '..'~') // Printable ASCII characters
        return buildString {
            repeat(length) {
                append(characters.random())
            }
        }
    }

    @Test
    fun `fuzz test parseSpecialChars`() {
        repeat(1000) { // Run 1000 random tests
            val input = generateRandomInput()
            try {
                parseSpecialChars(input)
            } catch (exception: Exception) {
                System.err.println("Fuzz test failed for input: $input")
                exception.printStackTrace()
            }
        }
    }

    @Test
    fun `fuzz test parseSpecialChars with edge cases`() {
        val edgeCases = listOf(
            "",            // Empty string
            "@NUL",        // Incomplete control code
            "\\x",         // Incomplete hex notation
            "\\xGG",       // Invalid hex notation
            "@UNKNOWN;",   // Unknown control code
            "\\",          // Single backslash
            "@foo;\\xFF",  // Mixed valid and invalid sequences
            "@NUL;\\xFF;", // Control code with hex value
            "@;@;@;"       // Multiple semicolons
        )

        for (case in edgeCases) {
            try {
                parseSpecialChars(case)
            } catch (exception: Exception) {
                System.err.println("Fuzz test failed for input: $case")
                exception.printStackTrace()
            }
        }
    }


}



