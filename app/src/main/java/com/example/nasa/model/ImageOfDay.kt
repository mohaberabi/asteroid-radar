package com.example.nasa.model

import com.example.nasa.database.ImageOfDayDbModel

data class ImageOfDay(
    val url: String,
    val mediaType: String,
    val title: String,
) {
    companion object {
        val empty = ImageOfDay("", "", "")

    }
}


fun ImageOfDay.toDbModel(): ImageOfDayDbModel {

    return ImageOfDayDbModel(

        url = this.url,
        mediaType = this.mediaType,
        title = this.title,
    )
}