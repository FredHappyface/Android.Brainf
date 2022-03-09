//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[LanguageRules](index.md)

# LanguageRules

[androidJvm]\
open class [LanguageRules](index.md)

interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ...

## Constructors

| | |
|---|---|
| [LanguageRules](-language-rules.md) | [androidJvm]<br>fun [LanguageRules](-language-rules.md)() |

## Functions

| Name | Summary |
|---|---|
| [createHighlighting](create-highlighting.md) | [androidJvm]<br>fun [createHighlighting](create-highlighting.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>Combine matching rules |
| [matchBrackets](match-brackets.md) | [androidJvm]<br>private fun [matchBrackets](match-brackets.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Brackets |
| [matchData](match-data.md) | [androidJvm]<br>private fun [matchData](match-data.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Data |
| [matchIO](match-i-o.md) | [androidJvm]<br>private fun [matchIO](match-i-o.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on IO |
| [matchPointer](match-pointer.md) | [androidJvm]<br>private fun [matchPointer](match-pointer.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Pointer |

## Inheritors

| Name |
|---|
| [SpannableHighlighter](../-spannable-highlighter/index.md) |
