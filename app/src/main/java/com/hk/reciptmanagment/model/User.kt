package com.hk.reciptmanagment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class User @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var firstName: String,
    var lastName: String,
    var userName: String,
    var phoneNumber: String,
    var password: String
) {
    constructor() : this(0, "", "", "", "", "") {}
}