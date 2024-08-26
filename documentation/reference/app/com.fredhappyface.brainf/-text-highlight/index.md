//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[TextHighlight](index.md)

# TextHighlight

open class [TextHighlight](index.md)(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150)

TextHighlight provides convenience functions to apply colours to an edit text

var colours: Colours = ColoursDark() var languageRules: LanguageRules = LanguageRulesJava() val codeEditText: EditText = findViewById(R.id.codeHighlight) val textHighlight = TextHighlight( codeEditText, languageRules, colours ) textHighlight.start()

#### Parameters

androidJvm

| | |
|---|---|
| targetEditText | EditText |
| languageRules | LanguageRules |
| colours | Colours |
| timeDelay | Long |

## Constructors

| | |
|---|---|
| [TextHighlight](-text-highlight.md) | [androidJvm]<br>constructor(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150) |

## Functions

| Name | Summary |
|---|---|
| [end](end.md) | [androidJvm]<br>open fun [end](end.md)()<br>Stop/end the TextHighlight functionality as applies to targetEditText |
| [start](start.md) | [androidJvm]<br>open fun [start](start.md)()<br>Start the TextHighlight functionality as applies to targetEditText |
