package com.fredhappyface.brainf


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BufferAdapter(private val data: Array<Int>) :
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
