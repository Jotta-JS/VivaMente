package com.example.vivamente.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remedios")
data class Remedy(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val userId: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val pills: Int,
    @ColumnInfo val quantityInMg: Int,
    @ColumnInfo val time: String,
    @ColumnInfo val daysLeft: Int,
    @ColumnInfo val color: String
)
