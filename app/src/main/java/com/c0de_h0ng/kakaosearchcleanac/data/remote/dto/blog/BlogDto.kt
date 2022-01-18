package com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog

import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog

data class BlogDto(
    val documents: List<Document>,
    val meta: Meta
)

fun BlogDto.toBlog(): List<Blog> {
    val result = mutableListOf<Blog>()
    for (i in documents.indices) {
        with(documents[i]) {
            result.add(
                Blog(
                    blogName = this.blogName,
                    contents = this.contents,
                    datetime = this.datetime,
                    thumbnail = this.datetime,
                    title = this.title,
                    url = this.url
                )
            )
        }
    }
    return result

}