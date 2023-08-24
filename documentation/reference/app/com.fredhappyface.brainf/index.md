//[app](../../index.md)/[com.fredhappyface.brainf](index.md)

# Package com.fredhappyface.brainf

## Types

| Name | Summary |
|---|---|
| [ActivityAbout](-activity-about/index.md) | [androidJvm]<br>class [ActivityAbout](-activity-about/index.md) : [AppCompatActivity](-activity-themable/index.md)<br>ActivityAbout class inherits from the AppCompatActivity class - provides the about view |
| [ActivityMain](-activity-main/index.md) | [androidJvm]<br>class [ActivityMain](-activity-main/index.md) : [AppCompatActivity](-activity-themable/index.md)<br>ActivityMain class inherits from the AppCompatActivity class - provides the settings view |
| [ActivitySettings](-activity-settings/index.md) | [androidJvm]<br>class [ActivitySettings](-activity-settings/index.md) : [AppCompatActivity](-activity-themable/index.md)<br>ActivitySettings class inherits from the AppCompatActivity class - provides the settings view |
| [AppCompatActivity](-activity-themable/index.md) | [androidJvm]<br>open class [AppCompatActivity](-activity-themable/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)<br>AppCompatActivity class inherits from the AppCompatActivity class - AppCompatActivity with custom themes. Overrides onCreate and onResume to set the theme |
| [BrainfInterpreter](-brainf-interpreter/index.md) | [androidJvm]<br>class [BrainfInterpreter](-brainf-interpreter/index.md)(programText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>BrainfInterpreter |
| [Colours](-colours/index.md) | [androidJvm]<br>interface [Colours](-colours/index.md)<br>interface Colours implemented by ColoursDark and ColoursLight |
| [ColoursDark](-colours-dark/index.md) | [androidJvm]<br>class [ColoursDark](-colours-dark/index.md) : [Colours](-colours/index.md)<br>ColoursDark implements interface Colours |
| [ColoursLight](-colours-light/index.md) | [androidJvm]<br>class [ColoursLight](-colours-light/index.md) : [Colours](-colours/index.md)<br>ColoursLight implements interface Colours |
| [DelayedTextWatch](-delayed-text-watch/index.md) | [androidJvm]<br>private class [DelayedTextWatch](-delayed-text-watch/index.md)(timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), action: ([CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [TextWatcher](https://developer.android.com/reference/kotlin/android/text/TextWatcher.html)<br>DelayedTextWatch. Override the onTextChanged function to apply some action after a timer |
| [LanguageRules](-language-rules/index.md) | [androidJvm]<br>open class [LanguageRules](-language-rules/index.md)<br>interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ... |
| [RuleMatch](-rule-match/index.md) | [androidJvm]<br>data class [RuleMatch](-rule-match/index.md)(ruleName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), endIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Rule match representation |
| [SpannableHighlighter](-spannable-highlighter/index.md) | [androidJvm]<br>open class [SpannableHighlighter](-spannable-highlighter/index.md)(colours: [Colours](-colours/index.md)) : [LanguageRules](-language-rules/index.md)<br>SpannableHighlighter class used to apply ruleMatches: List<RuleMatch> to a spannable: Spannable using specific languageRules and colours |
| [TextHighlight](-text-highlight/index.md) | [androidJvm]<br>open class [TextHighlight](-text-highlight/index.md)(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](-language-rules/index.md), colours: [Colours](-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))<br>TextHighlight provides convenience functions to apply colours to an edit text |

## Properties

| Name | Summary |
|---|---|
| [MAX_FILE_SIZE](-m-a-x_-f-i-l-e_-s-i-z-e.md) | [androidJvm]<br>private const val [MAX_FILE_SIZE](-m-a-x_-f-i-l-e_-s-i-z-e.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
