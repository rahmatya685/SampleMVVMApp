package ir.zngis.yaraapplication.repository.dto

import ir.zngis.yaraapplication.repository.model.Movie
import java.util.*

class SearchResponse(
    val Search: List<Movie> = mutableListOf(),

    val totalResults: String = "",


    val Response: String = ""
){


}