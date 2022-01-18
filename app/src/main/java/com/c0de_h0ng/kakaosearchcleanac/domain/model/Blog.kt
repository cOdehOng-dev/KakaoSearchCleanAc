package com.c0de_h0ng.kakaosearchcleanac.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
data class Blog(
    @SerializedName("blogname")
    val blogName: String,
    val contents: String,
    val datetime: String,
    val thumbnail: String,
    val title: String,
    val url: String
)
