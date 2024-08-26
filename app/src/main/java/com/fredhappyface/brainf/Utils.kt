package com.fredhappyface.brainf


fun parseSpecialChars(input: String): String {
    val result = StringBuilder()
    var i = 0

    while (i < input.length) {
        when (input[i]) {
            '\\' -> {
                if (i + 1 < input.length) {
                    when (input[i + 1]) {
                        '\\' -> result.append('\\').also { i++ }
                        'x' -> {
                            // Handle hex notation
                            val hexString = input.substring(i + 2, minOf(i + 4, input.length))
                            result.append(
                                hexString.toIntOrNull(16)?.toChar() ?: ("\\x${hexString}")
                            )
                            i += 3
                        }

                        else -> result.append('\\') // Handle other backslash cases
                    }
                } else {result.append('\\')}

                }

            '@' -> {
                // Find the end of the control code token (semicolon or end of string)
                val endIndex = input.indexOf(';', i)
                if (endIndex == -1) {
                    // No semicolon found, treat the rest of the string as literal
                    result.append('@')
                } else {
                    val controlCode = input.substring(i + 1, endIndex + 1)
                    val controlCodeUpper = controlCode.uppercase()
                    when (controlCodeUpper) {
                        "NUL;" -> result.append('\u0000')
                        "SOH;" -> result.append('\u0001')
                        "STX;" -> result.append('\u0002')
                        "ETX;" -> result.append('\u0003')
                        "EOT;" -> result.append('\u0004')
                        "ENQ;" -> result.append('\u0005')
                        "ACK;" -> result.append('\u0006')
                        "BEL;" -> result.append('\u0007')
                        "BS;"  -> result.append('\u0008')
                        "HT;"  -> result.append('\u0009')
                        "LF;"  -> result.append('\u000A')
                        "VT;"  -> result.append('\u000B')
                        "FF;"  -> result.append('\u000C')
                        "CR;"  -> result.append('\u000D')
                        "SO;"  -> result.append('\u000E')
                        "SI;"  -> result.append('\u000F')
                        "DLE;" -> result.append('\u0010')
                        "DC1;" -> result.append('\u0011')
                        "DC2;" -> result.append('\u0012')
                        "DC3;" -> result.append('\u0013')
                        "DC4;" -> result.append('\u0014')
                        "NAK;" -> result.append('\u0015')
                        "SYN;" -> result.append('\u0016')
                        "ETB;" -> result.append('\u0017')
                        "CAN;" -> result.append('\u0018')
                        "EM;" -> result.append('\u0019')
                        "SUB;" -> result.append('\u001A')
                        "ESC;" -> result.append('\u001B')
                        "FS;" -> result.append('\u001C')
                        "GS;" -> result.append('\u001D')
                        "RS;" -> result.append('\u001E')
                        "US;" -> result.append('\u001F')
                        "SP;" -> result.append('\u0020')
                        else -> result.append('@').append(controlCode) // Handle unknown control codes
                    }
                    i = endIndex // Move index past the semicolon
                }
            }            else -> result.append(input[i])
        }
        i++
    }

    return result.toString()
}
