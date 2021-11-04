package com.fredhappyface.brainf

import android.graphics.Color
import android.text.style.ForegroundColorSpan

/**
 * ColoursDark implements interface Colours
 */
class ColoursDark : Colours {
	override fun getColour(type: String): ForegroundColorSpan {
		val colourMap = mapOf(
			"io" to "#C077DF",
			"pointer" to "#D66C75",
			"bracket" to "#CB9A64",
			"comment" to "#555862",
			"data" to "#68B6C2"
		)
		return ForegroundColorSpan(Color.parseColor(colourMap[type]))
	}
}
