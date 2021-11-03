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
	private val mBuffer = Array(30000) { 0 }
	private var mBufferPointer = 0
	private var mInstructionPointer = 0
	private val mInstruction = programText
	private val mOutputBuffer = StringBuilder()
	private var mInputPointer = 0
	private var mInputBuffer = input

	/**
	 * execute the programText, with the input specified in the constructor
	 *
	 * @return String: OutputBuffer.toString()
	 */
	fun execute(): String {
		while (mInstructionPointer < mInstruction.length) {
			when (mInstruction[mInstructionPointer]) {
				'+' -> plus()
				'-' -> minus()
				'>' -> gt()
				'<' -> lt()
				'.' -> period()
				',' -> comma()
				'[' -> leftBracket()
				']' -> rightBracket()
			}
			mInstructionPointer++
		}
		return mOutputBuffer.toString()
	}

	/**
	 * Increment (increase by one) the byte at the data pointer.
	 *
	 */
	private fun plus() {
		if (mBuffer[mBufferPointer] < Int.MAX_VALUE) {
			mBuffer[mBufferPointer]++
		} else {
			throw IllegalStateException("Byte at the data pointer cannot exceed Int.MAX_VALUE (instruction={$mInstructionPointer:'${mInstruction[mInstructionPointer]}'})")
		}
	}

	/**
	 * Decrement (decrease by one) the byte at the data pointer.
	 *
	 */
	private fun minus() {
		if (mBuffer[mBufferPointer] > Int.MIN_VALUE) {
			mBuffer[mBufferPointer]--
		} else {
			throw IllegalStateException("Byte at the data pointer cannot precede Int.MIN_VALUE (instruction={$mInstructionPointer:'${mInstruction[mInstructionPointer]}'})")
		}
	}

	/**
	 * Increment the data pointer (to point to the next cell to the right).
	 *
	 */
	private fun gt() {
		if (mBufferPointer < mBuffer.size) {
			mBufferPointer++
		} else {
			throw IllegalStateException("Data pointer cannot exceed ${mBuffer.size} (instruction={$mInstructionPointer:'${mInstruction[mInstructionPointer]}'})")
		}
	}

	/**
	 * Decrement the data pointer (to point to the next cell to the left).
	 *
	 */
	private fun lt() {
		if (mBufferPointer > 0) {
			mBufferPointer--
		} else {
			throw IllegalStateException("Data pointer cannot precede 0 (instruction={$mInstructionPointer:'${mInstruction[mInstructionPointer]}'})")
		}
	}

	/**
	 * Output the byte at the data pointer.
	 *
	 */
	private fun period() {
		mOutputBuffer.append((mBuffer[mBufferPointer] and 0b11111111).toChar())
	}

	/**
	 * Accept one byte of input, storing its value in the byte at the data pointer.
	 *
	 */
	private fun comma() {
		if (mInputPointer < mInputBuffer.length) {
			mBuffer[mBufferPointer] = mInputBuffer[mInputPointer].code and 0b11111111
			mInputPointer++
		} else {
			throw IllegalStateException("Insufficient input supplied. Must be > ${mInputBuffer.length} (instruction={$mInstructionPointer:'${mInstruction[mInstructionPointer]}'})")
		}
	}

	/**
	 * If the byte at the data pointer is zero, then instead of moving the instruction pointer
	 * forward to the next command, jump it forward to the command after the matching ] command.
	 *
	 */
	private fun leftBracket() {
		if (mBuffer[mBufferPointer] == 0) {
			var brackets = 0
			while (mInstructionPointer < mInstruction.length) {
				mInstructionPointer++
				when (mInstruction[mInstructionPointer]) {
					'[' -> brackets++
					']' -> if (brackets == 0) {
						break
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
		if (mBuffer[mBufferPointer] > 0) {
			var brackets = 0
			while (mInstructionPointer > 0) {
				mInstructionPointer--
				when (mInstruction[mInstructionPointer]) {
					']' -> brackets++
					'[' -> if (brackets == 0) {
						break
					} else {
						brackets--
					}
				}
			}
		}
	}


}