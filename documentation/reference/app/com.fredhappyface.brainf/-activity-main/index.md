//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[ActivityMain](index.md)

# ActivityMain

[androidJvm]\
class [ActivityMain](index.md) : [AppCompatActivity](../-activity-themable/index.md)

ActivityMain class inherits from the AppCompatActivity class - provides the settings view

## Constructors

| | |
|---|---|
| [ActivityMain](-activity-main.md) | [androidJvm]<br>fun [ActivityMain](-activity-main.md)() |

## Functions

| Name | Summary |
|---|---|
| [doFileSave](do-file-save.md) | [androidJvm]<br>private fun [doFileSave](do-file-save.md)()<br>Call this when the user clicks menu -> save |
| [doNewFile](do-new-file.md) | [androidJvm]<br>private fun [doNewFile](do-new-file.md)()<br>Call this when the user clicks menu -> new file |
| [onCreate](on-create.md) | [androidJvm]<br>protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Override the onCreate method from AppCompatActivity adding the activity_main view and configuring the this.codeEditText, the textHighlight and the initial text |
| [onCreateOptionsMenu](on-create-options-menu.md) | [androidJvm]<br>open override fun [onCreateOptionsMenu](on-create-options-menu.md)(menu: [Menu](https://developer.android.com/reference/kotlin/android/view/Menu.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted menu on the title bar) |
| [onOptionsItemSelected](on-options-item-selected.md) | [androidJvm]<br>open override fun [onOptionsItemSelected](on-options-item-selected.md)(item: [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onOptionsItemSelected method. This is essentially a callback method triggered when the end user selects a menu item. Here we filter the item/ action selection and trigger a corresponding action. E.g. action_open -> startFileOpen() |
| [onSaveInstanceState](on-save-instance-state.md) | [androidJvm]<br>protected open override fun [onSaveInstanceState](on-save-instance-state.md)(outState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html))<br>Override onSaveInstanceState to save the _languageID and _uri when recreating the activity |
| [readTextFromUri](read-text-from-uri.md) | [androidJvm]<br>private fun [readTextFromUri](read-text-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Read the file text from the URI |
| [run](run.md) | [androidJvm]<br>fun [run](run.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Run the interpreter |
| [showDialogMessageSave](show-dialog-message-save.md) | [androidJvm]<br>private fun [showDialogMessageSave](show-dialog-message-save.md)()<br>Show a 'saved' dialog. In a function as its reused a couple of times |
| [startFileOpen](start-file-open.md) | [androidJvm]<br>private fun [startFileOpen](start-file-open.md)()<br>Call this when the user clicks menu -> open |
| [startFileSaveAs](start-file-save-as.md) | [androidJvm]<br>private fun [startFileSaveAs](start-file-save-as.md)()<br>Call this when the user clicks menu -> save as |
| [writeTextToUri](write-text-to-uri.md) | [androidJvm]<br>private fun [writeTextToUri](write-text-to-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Write the file text to the URI |

## Properties

| Name | Summary |
|---|---|
| [codeEditText](code-edit-text.md) | [androidJvm]<br>private lateinit var [codeEditText](code-edit-text.md): [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html) |
| [completeFileOpen](complete-file-open.md) | [androidJvm]<br>private val [completeFileOpen](complete-file-open.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;<br>Handles ACTION_OPEN_DOCUMENT result and sets this.uri, mLanguageID and mCodeEditText |
| [completeFileSaveAs](complete-file-save-as.md) | [androidJvm]<br>private val [completeFileSaveAs](complete-file-save-as.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;<br>Handles ACTION_CREATE_DOCUMENT result and sets this.uri, mLanguageID and triggers writeTextToUri |
| [uri](uri.md) | [androidJvm]<br>private var [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode (custom request code); _readRequestCode (request code for reading a file) |
