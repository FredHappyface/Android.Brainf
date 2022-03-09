package com.fredhappyface.brainf

/**
 * BrainfInterpreter
 *
 * @constructor
 *
 *
 * @param programText string of instructions eg. ",[.-]"
 * @param input string of predefined input eg. "abc"
 */
class BrainfInterpreter(programText: String, input: String) {
	private val buffer = Array(30000) { 0 }
	private var bufferPointer = 0
	private var instructionPointer = 0
	private val instruction = programText
	private val outputBuffer = StringBuilder()
	private var inputPointer = 0
	private var inputBuffer = input
	private var instructionsExecutedCounter = 0
	private var iOCounter = 0

	/**
	 * execute the programText, with the input specified in the constructor
	 *
	 * @return String: OutputBuffer.toString()
	 */
	fun execute(): String {
		while (this.instructionPointer < this.instruction.length && this.iOCounter < 16384 && this.instructionsExecutedCounter < 1048576) {
			when (this.instruction[this.instructionPointer]) {
				'+' -> plus()
				'-' -> minus()
				'>' -> gt()
				'<' -> lt()
				'.' -> {
					this.iOCounter++
					period()
				}
				',' -> {
					this.iOCounter++
					comma()
				}
				'[' -> leftBracket()
				']' -> rightBracket()
			}
			this.instructionsExecutedCounter++
			this.instructionPointer++
		}
		return this.outputBuffer.toString()
	}

	/**
	 * Increment (increase by one) the byte at the data pointer.
	 *
	 */
	private fun plus() {
		if (this.buffer[this.bufferPointer] < Int.MAX_VALUE) {
			this.buffer[this.bufferPointer]++
		} else {
			throw IllegalStateException("Byte at the data pointer cannot exceed Int.MAX_VALUE (instruction={${this.instructionPointer}:'${this.instruction[this.instructionPointer]}'})")
		}
	}

	/**
	 * Decrement (decrease by one) the byte at the data pointer.
	 *
	 */
	private fun minus() {
		if (this.buffer[this.bufferPointer] > Int.MIN_VALUE) {
			this.buffer[this.bufferPointer]--
		} else {
			throw IllegalStateException("Byte at the data pointer cannot precede Int.MIN_VALUE (instruction={${this.instructionPointer}:'${this.instruction[this.instructionPointer]}'})")
		}
	}

	/**
	 * Increment the data pointer (to point to the next cell to the right).
	 *
	 */
	private fun gt() {
		if (this.bufferPointer < this.buffer.size - 1) {
			this.bufferPointer++
		} else {
			throw IllegalStateException("Data pointer cannot exceed ${this.buffer.size - 1} (instruction={${this.instructionPointer}:'${this.instruction[this.instructionPointer]}'})")
		}
	}

	/**
	 * Decrement the data pointer (to point to the next cell to the left).
	 *
	 */
	private fun lt() {
		if (this.bufferPointer > 0) {
			this.bufferPointer--
		} else {
			throw IllegalStateException("Data pointer cannot precede 0 (instruction={${this.instructionPointer}:'${this.instruction[this.instructionPointer]}'})")
		}
	}

	/**
	 * Output the byte at the data pointer.
	 *
	 */
	private fun period() {
		this.outputBuffer.append((this.buffer[this.bufferPointer] and 0b11111111).toChar())
	}

	/**
	 * Accept one byte of input, storing its value in the byte at the data pointer.
	 *
	 */
	private fun comma() {
		if (this.inputPointer < this.inputBuffer.length) {
			this.buffer[this.bufferPointer] =
				this.inputBuffer[this.inputPointer].code and 0b11111111
			this.inputPointer++
		} else {
			this.buffer[this.bufferPointer] = 0
		}
	}

	/**
	 * If the byte at the data pointer is zero, then instead of moving the instruction pointer
	 * forward to the next command, jump it forward to the command after the matching ] command.
	 *
	 */
	private fun leftBracket() {
		if (this.buffer[this.bufferPointer] == 0) {
			var brackets = 0
			while (this.instructionPointer < this.instruction.length) {
				this.instructionPointer++
				if (this.instructionPointer >= this.instruction.length) {
					break
				}
				when (this.instruction[this.instructionPointer]) {
					'[' -> brackets++
					']' -> if (brackets == 0) {
						return
					} else {
						brackets--
					}
				}
			}
		}
	}

	/**
	 * If the byte at the data pointer is nonzero, then instead of moving the instruction pointer
	 * forward to the next command, jump it back to the command after the matching [ command.
	 *
	 */
	private fun rightBracket() {
		if (this.buffer[this.bufferPointer] > 0) {
			var brackets = 0
			while (this.instructionPointer > 0) {
				this.instructionPointer--
				when (this.instruction[this.instructionPointer]) {
					']' -> brackets++
					'[' -> if (brackets == 0) {
						return
					} else {
						brackets--
					}
				}
			}
		}
	}
}
