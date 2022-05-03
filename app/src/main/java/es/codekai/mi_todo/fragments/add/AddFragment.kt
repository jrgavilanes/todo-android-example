package es.codekai.mi_todo.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.data.viewmodel.ToDoViewModel
import es.codekai.mi_todo.databinding.FragmentAddBinding
import es.codekai.mi_todo.fragments.SharedViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private val mTodoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentAddBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {

        val title = binding.titleEt.text.toString()
        val priority = binding.prioritiesSpinner.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        if (mSharedViewModel.verifyDataFromUser(title, description)) {
            val newData = ToDoData(
                id = 0,
                title = title,
                priority = mSharedViewModel.parsePriority(priority),
                description = description
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "añadido guay", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
