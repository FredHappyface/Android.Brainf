//[app](../../index.md)/[com.fredhappyface.brainf](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ActivityAbout](-activity-about/index.md) | [androidJvm]<br>class [ActivityAbout](-activity-about/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)<br>ActivityAbout class inherits from the AppCompatActivity class - provides the about view |
| [ActivityMain](-activity-main/index.md) | [androidJvm]<br>class [ActivityMain](-activity-main/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)<br>ActivityMain class inherits from the AppCompatActivity class - provides the settings view |
| [BrainfInterpreter](-brainf-interpreter/index.md) | [androidJvm]<br>class [BrainfInterpreter](-brainf-interpreter/index.md)(programText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>BrainfInterpreter |
| [BufferAdapter](-buffer-adapter/index.md) | [androidJvm]<br>open class [BufferAdapter](-buffer-adapter/index.md)&lt;[T](-buffer-adapter/index.md)&gt;(val data: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](-buffer-adapter/index.md)&gt;) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[BufferAdapter.ViewHolder](-buffer-adapter/-view-holder/index.md)&gt; |
| [CharBufferAdapter](-char-buffer-adapter/index.md) | [androidJvm]<br>class [CharBufferAdapter](-char-buffer-adapter/index.md)(data: [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html)) : [BufferAdapter](-buffer-adapter/index.md)&lt;[Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)&gt; |
| [Colours](-colours/index.md) | [androidJvm]<br>interface [Colours](-colours/index.md)<br>interface Colours implemented by ColoursDark and ColoursLight |
| [ColoursDark](-colours-dark/index.md) | [androidJvm]<br>class [ColoursDark](-colours-dark/index.md) : [Colours](-colours/index.md)<br>ColoursDark implements interface Colours |
| [ColoursLight](-colours-light/index.md) | [androidJvm]<br>class [ColoursLight](-colours-light/index.md) : [Colours](-colours/index.md)<br>ColoursLight implements interface Colours |
| [LanguageRules](-language-rules/index.md) | [androidJvm]<br>open class [LanguageRules](-language-rules/index.md)<br>interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ... |
| [RuleMatch](-rule-match/index.md) | [androidJvm]<br>data class [RuleMatch](-rule-match/index.md)(val ruleName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val endIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Rule match representation |
| [SpannableHighlighter](-spannable-highlighter/index.md) | [androidJvm]<br>open class [SpannableHighlighter](-spannable-highlighter/index.md)(colours: [Colours](-colours/index.md)) : [LanguageRules](-language-rules/index.md)<br>SpannableHighlighter class used to apply ruleMatches: List<RuleMatch> to a spannable: Spannable using specific languageRules and colours |
| [TextHighlight](-text-highlight/index.md) | [androidJvm]<br>open class [TextHighlight](-text-highlight/index.md)(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](-language-rules/index.md), colours: [Colours](-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150)<br>TextHighlight provides convenience functions to apply colours to an edit text |

## Functions

| Name | Summary |
|---|---|
| [parseSpecialChars](parse-special-chars.md) | [androidJvm]<br>fun [parseSpecialChars](parse-special-chars.md)(input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
