package com.example.notesmvvm.nurtdinov.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notesmvvm.nurtdinov.database.firebase.AppFirebaseRepository
import com.example.notesmvvm.nurtdinov.database.room.AppRoomDatabase
import com.example.notesmvvm.nurtdinov.database.room.AppRoomRepository
import com.example.notesmvvm.nurtdinov.utilit.*

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})
            }
        }

    }
}