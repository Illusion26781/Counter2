package com.example.counter2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CounterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private val counterN: TextView = itemView.findViewById(R.id.CounterN)
    private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    private val incButton: Button = itemView.findViewById(R.id.incButton)

    fun bind(counter: Int, onEvent : (Event) -> Unit) {
        counterN.text = counter.toString()
        deleteButton.setOnClickListener {
            onEvent(Event.DeleteEvent())
        }
        incButton.setOnClickListener {
            onEvent(Event.IncEvent)
        }
    }
}

class CounterListAdapter(
        private val counters:MutableList<Int>,
        private val totalCounterChange: (Event) -> Unit
    ) : RecyclerView.Adapter<CounterViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        return CounterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.counter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val counter = counters[position]

        holder.bind(counter)
        {
            when(it)
            {
                is Event.IncEvent -> {
                    totalCounterChange(it)
                    counters[holder.adapterPosition]++
                    notifyItemChanged(holder.adapterPosition)
                }
                is Event.DeleteEvent -> {
                    totalCounterChange(Event.DeleteEvent(counters[holder.adapterPosition]))
                    counters.removeAt(holder.adapterPosition)
                    notifyItemRemoved(holder.adapterPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return counters.size
    }

    fun add() {
        counters.add(0)
        notifyItemInserted(counters.lastIndex)
    }


}