# Simple Guide to Using Brainf

Brainf is a minimalist programming language with only eight commands, known for its simplicity and
esoteric nature. Here's a brief overview to get you started:

## Brainf Commands

- **`>`**: Move the data pointer one cell to the right.
- **`<`**: Move the data pointer one cell to the left.
- **`+`**: Increment the byte at the data pointer.
- **`-`**: Decrement the byte at the data pointer.
- **`.`**: Output the byte at the data pointer as a character.
- **`,`**: Accept one byte of input and store it in the cell at the data pointer.
- **`[`**: Jump forward to the command after the matching `]` if the byte at the data pointer is zero.
- **`]`**: Jump back to the command after the matching `[` if the byte at the data pointer is non-zero.

## Example Program: "Hello, World!"

```brainfuck
++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.
```

This program prints "Hello, World!" to the screen

## Tips for Using Brainf

- **Memory**: Brainf operates on an array of memory cells (30,000 cells). The pointer starts at the
  first cell, and you can navigate through the array using `>` and `<`.
- **Loops**: Loops are created with `[` and `]`, which allow you to repeat a set of commands based
  on the value at the current memory cell.
- **Input/Output**: Use `,` to read input from the user and `.` to output characters.

## Online Resources for Learning More
To dive deeper into Brainf, check out these resources:

- [Brainf Language Wiki](https://esolangs.org/wiki/Brainfuck): A comprehensive wiki page with detailed information about Brainf and its variations.
- [Brainf Documentation](https://gist.github.com/roachhd/dce54bec8ba55fb17d3a): A github gist with examples and explanations of Brainf.
