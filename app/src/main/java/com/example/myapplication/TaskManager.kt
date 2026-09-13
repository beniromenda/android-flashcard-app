package com.example.myapplication

class TaskManager {
    //A class to manage the list of tasks
    private var nextId = 0
    private val _tasks = mutableListOf<Task>()
    val tasks: List<Task>
        get() = _tasks.toList()

    fun addTask(title: String){
        if (title.isBlank()) return
        _tasks.add(Task(id=nextId, title= title))
        nextId++
    }
    fun toggleTask(id: Int){
        val index = _tasks.indexOfFirst {it.id ==id}
        if (index != -1){
            val task = _tasks[index]
            _tasks[index] = task.copy(isDone=!task.isDone)
        }

    }
}