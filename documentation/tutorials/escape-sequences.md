# FredHappyface Brainf escape sequences

The latest version of Brainf supports special handling for ASCII control characters, expanding its
functionality for interpreting and processing text-based input. This document provides an overview
of the newly added features, focusing on two notation types: **hexadecimal notation** and
**control character notation**.

## 1. Hexadecimal Notation (`\xNN`)
The input processing can now interpret ASCII control characters represented in hexadecimal format.
This notation allows you to include any unprintable or control characters within your input string
by specifying their hexadecimal value.

- **Format:** `\xNN`, where `NN` is a two-digit hexadecimal code representing the ASCII character.
- **Example:**
	- Input: `\x00\x01`
	- Result: Converts to `\u0000\u0001`, representing the NUL and SOH control characters.

This notation is especially useful when you need to include control characters that are not
typically visible or printable, such as null bytes (`\x00`) or line feeds (`\x0A`).

## 2. Control Character Notation (`@NAME;`)
Another method for representing ASCII control characters is using symbolic names in the format
`@NAME;`. This format is case-insensitive and interprets named control characters within the input.

- **Format:** `@NAME;`, where `NAME` is a predefined ASCII control character name.
- **Example:**
	- Input: `@NUL;@SOH;`
	- Result: Converts to `\u0000\u0001`, representing the NUL and SOH control characters.

The full list of supported control character names includes the entire set of ASCII control codes,
from `@NUL;` (Null) to `@US;` (Unit Separator). You can also mix control character notation with
regular text, making it easy to embed control sequences within your strings.

## 3. Escaped Sequences (partial support)
When you need to include backslashes (`\`) in your input, the function also supports escape
sequences, note that **Escaped Backslash (`\\`)**: Represents a single backslash (`\`).

## 4. Handling Invalid Sequences
The input processing function is designed to handle invalid or unknown sequences gracefully:

- **Invalid Hex Notation**: If the input contains malformed or incomplete hex notation
  (e.g., `\xZZ`), the function will return the input as-is without converting it.
- **Unknown Control Characters**: If an unrecognized control character name is provided
  (e.g., `@UNKNOWN;`), the function will return the sequence without interpreting it.

## 5. Mixed Notation Support
You can mix both hex notation and control character notation within the same input string. For example,
an input like `@NUL;Hello\x02World`
