package es.codekai.mi_todo.fragments.update

import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.Priority
import es.codekai.mi_todo.databinding.FragmentUpdateBinding
import es.codekai.mi_todo.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel by viewModels<SharedViewModel>()

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
        binding.prioritiesSpinner.setSelection(parsePriority(args.currentItem.priority))
        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        binding.descriptionEt.setText(args.currentItem.description)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}
