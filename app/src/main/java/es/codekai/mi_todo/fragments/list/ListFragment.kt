package es.codekai.mi_todo.fragments.list

import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val mTodoViewListViewModel: ToDoListViewModel by viewModels()

    private inner class ToDoListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_txt)
        val description: TextView = itemView.findViewById(R.id.description_txt)
        val tarjeta: ConstraintLayout = itemView.findViewById(R.id.tarjeta)
    }

    private inner class ToDoListAdapter(var todos: List<ToDoData>) :
        RecyclerView.Adapter<ToDoListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListHolder {
            val view = layoutInflater.inflate(R.layout.row_layout, parent, false)
            return ToDoListHolder(view)
        }

        override fun onBindViewHolder(holder: ToDoListHolder, position: Int) {
            val todo = todos[position]
            holder.apply {
                title.text = todo.title
                description.text = todo.description
                tarjeta.setOnClickListener {
                    Toast.makeText(requireContext(), "$todo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun getItemCount(): Int {
            return todos.size
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        updateUI()

        return binding.root
    }

    private fun updateUI() {

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val todos = mTodoViewListViewModel.todos

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ToDoListAdapter(todos)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}
