package com.example.notesmvvm.nurtdinov.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_tables")
data class AppNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String = "",
    @ColumnInfo val desc: String = "",
    @ColumnInfo val time: String = "",
    val idFirebase : String = ""
):Serializable