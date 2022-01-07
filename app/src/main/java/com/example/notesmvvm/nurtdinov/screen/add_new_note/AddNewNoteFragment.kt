package com.example.notesmvvm.nurtdinov.screen.add_new_note

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesmvvm.nurtdinov.R
import com.example.notesmvvm.nurtdinov.databinding.FragmentAddNewNoteBinding
import com.example.notesmvvm.nurtdinov.database.room.entity.AppNote
import com.example.notesmvvm.nurtdinov.utilit.APP_ACTIVITY
import com.example.notesmvvm.nurtdinov.utilit.hideKeyboard
import com.example.notesmvvm.nurtdinov.utilit.showToast
import java.util.*


class AddNewNoteFragment : Fragment() {
    private var _binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(AddNewNoteFragmentViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_action_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = mBinding.inputTitleNote.text.toString()
        val desc = mBinding.inputDescNote.text.toString()
        val time = getCurrentTime()
        when (item.itemId) {
            R.id.menu_add -> {
                hideKeyboard()
                if (title.isEmpty()) {
                    showToast("Input Title!")
                } else {
                    mViewModel.insert(AppNote(title = title, desc = desc, time = time)) {
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(
            "MMM d hh:mm",
            Locale.getDefault()
        )
        return formatter.format((time))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}