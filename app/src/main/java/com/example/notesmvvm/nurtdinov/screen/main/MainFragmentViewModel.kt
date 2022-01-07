package com.example.notesmvvm.nurtdinov.screen.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notesmvvm.nurtdinov.utilit.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOut()
    }
}