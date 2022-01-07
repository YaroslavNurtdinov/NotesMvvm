package com.example.notesmvvm.nurtdinov.database.firebase

import androidx.lifecycle.LiveData
import com.example.notesmvvm.nurtdinov.database.DatabaseRepository
import com.example.notesmvvm.nurtdinov.database.room.entity.AppNote
import com.example.notesmvvm.nurtdinov.utilit.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository : DatabaseRepository {

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[TITLE] = note.title
        mapNote[DESC] = note.desc

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        if (AppPreference.getInitUser()) {
            initRefs()
            onSuccess()
        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess()
                }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess()
                        }
                        .addOnFailureListener { onFail(it.message.toString()) }
                }

        }

    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(AUTH.currentUser?.uid.toString())
    }

    override fun signOut() {
        AUTH.signOut()
    }
}