package es.codekai.mi_todo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.Priority
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val mTodoViewListViewModel: ToDoListViewModel by viewModels()

    private inner class ToDoListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_txt)
        val description: TextView = itemView.findViewById(R.id.description_txt)
        val card: ConstraintLayout = itemView.findViewById(R.id.tarjeta)
        val priorityIndicator: CardView = itemView.findViewById(R.id.priority_indicator)
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
                when (todo.priority) {
                    Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
                        resources.getColor(R.color.red, requireActivity().theme)
                    )
                    Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
                        resources.getColor(
                            R.color.yellow, requireActivity().theme
                        )
                    )
                    Priority.LOW -> priorityIndicator.setCardBackgroundColor(
                        resources.getColor(R.color.green, requireActivity().theme)
                    )
                }

                card.setOnClickListener {
                    val action =
                        ListFragmentDirections.actionListFragmentToUpdateFragment(todos[position])
                    itemView.findNavController().navigate(action)
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

        mTodoViewListViewModel.getAllData.observe(
            viewLifecycleOwner,
            Observer { data ->
                binding.recyclerView.adapter = ToDoListAdapter(data)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(
                requireContext(),
                getString(R.string.all_registers_are_deleted),
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle(getString(R.string.sure_you_want_delete_all))
        builder.create().show()
    }

}
