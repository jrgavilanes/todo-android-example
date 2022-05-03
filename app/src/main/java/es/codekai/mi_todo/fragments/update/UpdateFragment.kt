package es.codekai.mi_todo.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.data.viewmodel.ToDoViewModel
import es.codekai.mi_todo.databinding.FragmentUpdateBinding
import es.codekai.mi_todo.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel by viewModels<SharedViewModel>()
    private val mToDoViewModel by viewModels<ToDoViewModel>()

    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        binding.titleEt.setText(args.currentItem.title)
        binding.prioritiesSpinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
//        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        binding.descriptionEt.setText(args.currentItem.description)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> updateItem()
            R.id.menu_delete -> deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = binding.titleEt.text.toString()
        val description = binding.descriptionEt.text.toString()
        val getPriority = binding.prioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val updateItem = ToDoData(
                id = args.currentItem.id,
                title = title,
                priority = mSharedViewModel.parsePriority(getPriority),
                description = description
            )
            mToDoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Actualizado '$title'", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteData(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Se ha borrado '${args.currentItem.title}'",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Seguro que quieres borrar '${args.currentItem.title}'?")
        builder.create().show()
    }
}
