package com.fredhappyface.brainf

import android.graphics.Color
import android.text.style.ForegroundColorSpan

/**
 * ColoursLight implements interface Colours
 */
class ColoursLight : Colours {
	override fun getColour(type: String): ForegroundColorSpan {
		val colourMap = mapOf(
			"io" to "#9E25A6",
			"pointer" to "#BF1243",
			"bracket" to "#BA8400",
			"comment" to "#383A42",
			"data" to "#5077F4"
		)
		return ForegroundColorSpan(Color.parseColor(colourMap[type]))
	}
}
