//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[ActivityMain](index.md)

# ActivityMain

[androidJvm]\
class [ActivityMain](index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)

ActivityMain class inherits from the AppCompatActivity class - provides the settings view

## Constructors

| | |
|---|---|
| [ActivityMain](-activity-main.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [fillTable](fill-table.md) | [androidJvm]<br>fun &lt;[T](fill-table.md)&gt; [fillTable](fill-table.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), bufferAdapter: [BufferAdapter](../-buffer-adapter/index.md)&lt;[T](fill-table.md)&gt;) |
| [onCreateOptionsMenu](on-create-options-menu.md) | [androidJvm]<br>open override fun [onCreateOptionsMenu](on-create-options-menu.md)(menu: [Menu](https://developer.android.com/reference/kotlin/android/view/Menu.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted menu on the title bar) |
| [onOptionsItemSelected](on-options-item-selected.md) | [androidJvm]<br>open override fun [onOptionsItemSelected](on-options-item-selected.md)(item: [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onOptionsItemSelected method. This is essentially a callback method triggered when the end user selects a menu item. Here we filter the item/ action selection and trigger a corresponding action. E.g. action_open -> startFileOpen() |
| [run](run.md) | [androidJvm]<br>fun [run](run.md)(ignoreView: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Run the interpreter |
