package com.example.thinkpad.Models

import androidx.room.ColumnInfo

    data class User(
        @ColumnInfo(name = "exist")   val note: Boolean?
    ):java.io.Serializable
