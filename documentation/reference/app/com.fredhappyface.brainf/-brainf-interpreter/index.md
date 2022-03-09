//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[BrainfInterpreter](index.md)

# BrainfInterpreter

[androidJvm]\
class [BrainfInterpreter](index.md)(programText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

BrainfInterpreter

## Parameters

androidJvm

| | |
|---|---|
| programText | string of instructions eg. ",.-" |
| input | string of predefined input eg. "abc" |

## Constructors

| | |
|---|---|
| [BrainfInterpreter](-brainf-interpreter.md) | [androidJvm]<br>fun [BrainfInterpreter](-brainf-interpreter.md)(programText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [comma](comma.md) | [androidJvm]<br>private fun [comma](comma.md)()<br>Accept one byte of input, storing its value in the byte at the data pointer. |
| [execute](execute.md) | [androidJvm]<br>fun [execute](execute.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>execute the programText, with the input specified in the constructor |
| [gt](gt.md) | [androidJvm]<br>private fun [gt](gt.md)()<br>Increment the data pointer (to point to the next cell to the right). |
| [leftBracket](left-bracket.md) | [androidJvm]<br>private fun [leftBracket](left-bracket.md)()<br>If the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command. |
| [lt](lt.md) | [androidJvm]<br>private fun [lt](lt.md)()<br>Decrement the data pointer (to point to the next cell to the left). |
| [minus](minus.md) | [androidJvm]<br>private fun [minus](minus.md)()<br>Decrement (decrease by one) the byte at the data pointer. |
| [period](period.md) | [androidJvm]<br>private fun [period](period.md)()<br>Output the byte at the data pointer. |
| [plus](plus.md) | [androidJvm]<br>private fun [plus](plus.md)()<br>Increment (increase by one) the byte at the data pointer. |
| [rightBracket](right-bracket.md) | [androidJvm]<br>private fun [rightBracket](right-bracket.md)()<br>If the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command. |

## Properties

| Name | Summary |
|---|---|
| [buffer](buffer.md) | [androidJvm]<br>private val [buffer](buffer.md): [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| [bufferPointer](buffer-pointer.md) | [androidJvm]<br>private var [bufferPointer](buffer-pointer.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [inputBuffer](input-buffer.md) | [androidJvm]<br>private var [inputBuffer](input-buffer.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [inputPointer](input-pointer.md) | [androidJvm]<br>private var [inputPointer](input-pointer.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [instruction](instruction.md) | [androidJvm]<br>private val [instruction](instruction.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [instructionPointer](instruction-pointer.md) | [androidJvm]<br>private var [instructionPointer](instruction-pointer.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [instructionsExecutedCounter](instructions-executed-counter.md) | [androidJvm]<br>private var [instructionsExecutedCounter](instructions-executed-counter.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [iOCounter](i-o-counter.md) | [androidJvm]<br>private var [iOCounter](i-o-counter.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [outputBuffer](output-buffer.md) | [androidJvm]<br>private val [outputBuffer](output-buffer.md): [StringBuilder](https://developer.android.com/reference/kotlin/java/lang/StringBuilder.html) |
