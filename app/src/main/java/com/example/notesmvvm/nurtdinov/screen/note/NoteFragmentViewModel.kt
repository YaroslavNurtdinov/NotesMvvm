package com.example.notesmvvm.nurtdinov.screen.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.nurtdinov.database.room.entity.AppNote
import com.example.notesmvvm.nurtdinov.utilit.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(note: AppNote, onSuccess:() ->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
}