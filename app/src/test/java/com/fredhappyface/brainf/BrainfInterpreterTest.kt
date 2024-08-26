package com.fredhappyface.brainf


import org.junit.Assert.assertEquals
import org.junit.Test

class BrainfInterpreterTest {


    @Test
    fun testHelloWorld() {
        val brainf = BrainfInterpreter(
            "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.",
            ""
        )
        val (output, buffer) = brainf.execute()
        assertEquals("Hello World!\n", output)
    }

    @Test
    fun testZeroCharacter() {
        val brainf = BrainfInterpreter(".", "")
        val (output, buffer) = brainf.execute()
        assertEquals("\u0000", output)
    }

    @Test
    fun testDollarCharacter() {
        val brainf = BrainfInterpreter(">++++[<+++++++++>-]<.", "")
        val (output, buffer) = brainf.execute()
        assertEquals("$", output)
    }


    @Test
    fun testInputHandling() {
        // Mock input, for example 'X'
        val brainf = BrainfInterpreter(",.[-]", "X")
        val (output, buffer) = brainf.execute()
        assertEquals("X", output)
    }

    @Test
    fun testNoOperation() {
        val brainf = BrainfInterpreter("", "")
        val (output, buffer) = brainf.execute()
        assertEquals("", output)
    }

    @Test
    fun testInvalidCode() {
        val brainf = BrainfInterpreter("+[", "")
        val (output, buffer) = brainf.execute()
        assertEquals("", output)
    }

}