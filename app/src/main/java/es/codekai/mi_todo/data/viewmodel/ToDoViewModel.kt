package es.codekai.mi_todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import es.codekai.mi_todo.data.ToDoDatabase
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).todoDao()
    private val repository: ToDoRepository

    private val getAllData: LiveData<List<ToDoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

}