package com.example.counter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalCount = findViewById<TextView>(R.id.totalCounter)
        var total = 0
        val counters = mutableListOf(0)
        val adapter = CounterListAdapter(counters){
            when(it)
            {
                is Event.IncEvent -> {
                    total++
                }
                is Event.DeleteEvent -> {
                    total-=it.count
                }
            }
            totalCount.text = total.toString()

        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.addCounter).setOnClickListener {
            adapter.add()
        }


    }
}