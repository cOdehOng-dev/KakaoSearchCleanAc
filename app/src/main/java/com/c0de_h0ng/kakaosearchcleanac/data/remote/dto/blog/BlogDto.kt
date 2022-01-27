package com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog

import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog

data class BlogDto(
    val documents: List<Document>,
    val meta: Meta
)

fun BlogDto.toBlog(): List<Blog> {
    return documents.toList().map {
        Blog(
            blogName = it.blogName,
            contents = it.contents,
            datetime = it.datetime,
            thumbnail = it.datetime,
            title = it.title,
            url = it.url
        )
    }
}