package com.fredhappyface.brainf

import android.text.style.ForegroundColorSpan

/**
 * interface Colours implemented by ColoursDark and ColoursLight
 */
interface Colours {
	/**
	 * Get a colour for a given type
	 *
	 * eg colourMap = ("keyword" to "red", "annotation" to "red", "constant" to "#orange", "comment" to "grey", "class" to "green", "import" to "blue")
	 *
	 * @param type String eg "keyword"
	 * @return ForegroundColorSpan ForegroundColorSpan(Color.parseColor(colourMap[type])) -> "red" if type="keyword"
	 */
	fun getColour(type: String): ForegroundColorSpan
}
