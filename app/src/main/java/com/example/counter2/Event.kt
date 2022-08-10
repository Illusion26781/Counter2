package com.example.counter2

sealed class Event{
    class DeleteEvent(val count: Int = 0) : Event()
    object IncEvent : Event()
}
