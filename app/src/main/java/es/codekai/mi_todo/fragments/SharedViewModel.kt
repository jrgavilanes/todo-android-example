package es.codekai.mi_todo.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.codekai.mi_todo.data.models.Priority

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return title.isNotBlank() && description.isNotBlank()
    }

    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High priority" -> Priority.HIGH
            "Medium priority" -> Priority.MEDIUM
            "Low priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }
}
