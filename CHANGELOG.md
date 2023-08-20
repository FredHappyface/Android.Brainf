# Changelog

All major and minor version changes will be documented in this file. Details of
patch-level version changes can be found in [commit messages](../../commits/master).

<!--
## Next_Ver - 2022/xx/xx
-->

## 20230820 - 2023/08/20

- Update dependencies

## 20220311 - 2022/03/11

- New Feature: Add Gradle tasks
	- ktlintCheck (`gradlew ktlintCheck`): run ktlint over the codebase
	- genDocs (`gradlew genDocs`): generate the api reference using dokka
- New Feature: Update navbar theme (dark/light rather than the app accent colour)
- Bugfix: Empty input is no longer considered an error https://github.com/FredHappyface/Android.Brainf/issues/4
	- For example, the cat program
		```bf
		,[.,]
		```
		Input: hello, output is now as expected
- Removed included apks, use github releases instead

## 20220110 - 2022/01/10

- Update dependencies
- Set file size limit to 1Mb to defend against `java.lang.OutOfMemoryError`
- Fix BrainfInterpreter.gt and BrainfInterpreter.leftBracket to defend against `java.lang.StringIndexOutOfBoundsException`

## 20211104 - 2021/11/04

- Refactor brainfInterpreter function to BrainfInterpreter
- Rebase on FHCode
	- Add feature description
	- Use cards (similar style to EweSticker)
	- Add comments to TextHighlight
	- Update fonts
	- Code clean up + documentation improvements
	- The new target SDK version is 31 (Android 12) - previously 30 (Android 11)
	- Add launcher Shortcuts for Settings, About, New File
- Update app icon
- Update screenshots

## 20211030 - 2021/10/30

- Gradle to kotlin
- Improve documentation
- Code clean up
- replace deprecated onActivityResult with registerForActivityResult logic
- tidy up files committed to git

## 20210501 - 2021/05/01

- Update gradle wrapper
- Update metadata
- Misc tweaks

## 20210430 - 2021/04/30

- Modernize the codebase
	- Convert to Kotlin
	- Optimizations
- Add fastlane metadata https://f-droid.org/en/docs/All_About_Descriptions_Graphics_and_Screenshots/#fastlane-structure
