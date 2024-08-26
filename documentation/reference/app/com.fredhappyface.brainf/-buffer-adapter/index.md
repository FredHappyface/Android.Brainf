//[app](../../../index.md)/[com.fredhappyface.brainf](../index.md)/[BufferAdapter](index.md)

# BufferAdapter

open class [BufferAdapter](index.md)&lt;[T](index.md)&gt;(val data: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](index.md)&gt;) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[BufferAdapter.ViewHolder](-view-holder/index.md)&gt; 

#### Inheritors

| |
|---|
| [CharBufferAdapter](../-char-buffer-adapter/index.md) |

## Constructors

| | |
|---|---|
| [BufferAdapter](-buffer-adapter.md) | [androidJvm]<br>constructor(data: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](index.md)&gt;) |

## Types

| Name | Summary |
|---|---|
| [ViewHolder](-view-holder/index.md) | [androidJvm]<br>class [ViewHolder](-view-holder/index.md)(itemView: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html) |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | [androidJvm]<br>open override fun [getItemCount](get-item-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | [androidJvm]<br>open override fun [onBindViewHolder](on-bind-view-holder.md)(holder: [BufferAdapter.ViewHolder](-view-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onCreateViewHolder](on-create-view-holder.md) | [androidJvm]<br>open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [BufferAdapter.ViewHolder](-view-holder/index.md) |
