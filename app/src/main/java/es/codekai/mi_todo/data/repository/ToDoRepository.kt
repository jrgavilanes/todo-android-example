package es.codekai.mi_todo.data.repository

import androidx.lifecycle.LiveData
import es.codekai.mi_todo.data.ToDoDao
import es.codekai.mi_todo.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

}