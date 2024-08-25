package com.fredhappyface.brainf


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

open class BufferAdapter<T>(val data: Array<T>) :
	RecyclerView.Adapter<BufferAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.array_val, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.primaryText.text = data[position].toString()
		holder.secondaryText.text = position.toString()
	}

	override fun getItemCount(): Int {
		return data.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val primaryText: TextView = itemView.findViewById(R.id.primaryText)
		val secondaryText: TextView = itemView.findViewById(R.id.secondaryText)

	}
}

class CharBufferAdapter(data: Array<Char>) : BufferAdapter<Char>(data) {

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		var elem = data[position]
		holder.primaryText.text = when(elem.code){
			in 0..32 -> "\\x${elem.code.toString(16).padStart(2, '0')}"
			else -> elem.toString()
		}
		holder.secondaryText.text = position.toString()
	}

}
