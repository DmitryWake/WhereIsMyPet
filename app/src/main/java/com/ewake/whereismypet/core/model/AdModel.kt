package com.ewake.whereismypet.core.model

/**
 * @author Nikolaevskiy Dmitriy
 */
data class AdModel(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String
)

fun createTempAdsList(): List<AdModel> =
    listOf(
        AdModel(
            "1",
            "title 1",
            "description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 1",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
        AdModel(
            "2",
            "title 2",
            "description 2 description 2 description 2 description 2 description 2 description 2 description 2 description 2 description 2",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
        AdModel(
            "3",
            "title 3",
            "description 3 description 3 description 3 description 3 description 3 description 3 description 3 description 3 description 3",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
        AdModel(
            "4",
            "title 4",
            "description 4 description 4 description 4 description 4 description 4 description 4 description 4 description 4 description 4",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
        AdModel(
            "5",
            "title 5",
            "description 5 description 5 description 5 description 5 description 5 description 5 description 5 description 5 description 5",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
        AdModel(
            "6",
            "title 6",
            "description 6 description 6 description 6 description 6 description 6 description 6 description 6 description 6 description 6",
            "https://pic.rutubelist.ru/video/eb/f8/ebf8d86ddd388ef643c195927b2be660.jpg"
        ),
    )
