package es.codekai.mi_todo.fragments.list

import androidx.lifecycle.ViewModel
import es.codekai.mi_todo.data.models.Priority
import es.codekai.mi_todo.data.models.ToDoData

class ToDoListViewModel : ViewModel() {

    val todos = mutableListOf<ToDoData>()

    init {
        for (i in 1..10) {
            val todo = ToDoData(
                id = i,
                title = "Task $i",
                priority = Priority.LOW,
                description = "description task $i"
            )
            todos.add(todo)
        }
    }
}
