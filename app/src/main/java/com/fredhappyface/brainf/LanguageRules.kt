package com.fredhappyface.brainf

/**
 * interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ...
 */
open class LanguageRules {
	/**
	 * match on Pointer
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	private fun matchPointer(string: CharSequence): List<RuleMatch> {
		return "[<>]+".toRegex().findAll(string).map {
			RuleMatch("pointer", it.range.first, it.range.last + 1)
		}.toList()
	}

	/**
	 * match on Data
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	private fun matchData(string: CharSequence): List<RuleMatch> {
		return "[+-]+".toRegex().findAll(string).map {
			RuleMatch("data", it.range.first, it.range.last + 1)
		}.toList()
	}

	/**
	 * match on IO
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	private fun matchIO(string: CharSequence): List<RuleMatch> {
		return "[.,]+".toRegex().findAll(string).map {
			RuleMatch("io", it.range.first, it.range.last + 1)
		}.toList()
	}

	/**
	 * match on Brackets
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	private fun matchBrackets(string: CharSequence): List<RuleMatch> {
		return "[\\[\\]]+".toRegex().findAll(string).map {
			RuleMatch("bracket", it.range.first, it.range.last + 1)
		}.toList()
	}

	/**
	 * Combine matching rules
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun createHighlighting(string: CharSequence): List<RuleMatch> {
		return matchPointer(string) + matchData(string) + matchIO(string) +
				matchBrackets(string)
	}
}
