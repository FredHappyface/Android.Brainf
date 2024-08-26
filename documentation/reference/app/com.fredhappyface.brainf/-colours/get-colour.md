//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[Colours](index.md)/[getColour](get-colour.md)

# getColour

[androidJvm]\
abstract fun [getColour](get-colour.md)(type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ForegroundColorSpan](https://developer.android.com/reference/kotlin/android/text/style/ForegroundColorSpan.html)

Get a colour for a given type

eg colourMap = (&quot;keyword&quot; to &quot;red&quot;, &quot;annotation&quot; to &quot;red&quot;, &quot;constant&quot; to &quot;#orange&quot;, &quot;comment&quot; to &quot;grey&quot;, &quot;class&quot; to &quot;green&quot;, &quot;import&quot; to &quot;blue&quot;)

#### Return

ForegroundColorSpan ForegroundColorSpan(Color.parseColor(colourMap[type](get-colour.md))) -> &quot;red&quot; if type=&quot;keyword&quot;

#### Parameters

androidJvm

| | |
|---|---|
| type | String eg &quot;keyword&quot; |
