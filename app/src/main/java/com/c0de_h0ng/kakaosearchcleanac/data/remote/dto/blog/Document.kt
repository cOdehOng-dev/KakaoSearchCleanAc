package com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("blogname")
    val blogName: String,
    val contents: String,
    val datetime: String,
    val thumbnail: String,
    val title: String,
    val url: String
)