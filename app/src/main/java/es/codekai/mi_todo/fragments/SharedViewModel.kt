package es.codekai.mi_todo.fragments

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import es.codekai.mi_todo.R
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

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.red
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.yellow
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.green
                        )
                    )
                }
            }
        }
    }
}
