package com.metehanbolat.kotlinmvvmsoftwarelanguages.model

import com.google.gson.annotations.SerializedName

data class Languages(
    @SerializedName("name")
    val languagesNameModel: String?,
    @SerializedName("description")
    val languagesDescriptionModel: String?,
    @SerializedName("icon")
    val imageUrlModel: String?)