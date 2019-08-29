package ir.zngis.yaraapplication.repository.remote

import io.reactivex.Single
import ir.zngis.yaraapplication.repository.dto.SearchResponse
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface EntityService {


    @GET("?apikey=3e974fca&s=batman")
    fun getMoives(): Single<SearchResponse>

    @GET("?apikey=3e974fca")
    fun getMoiveDetail(@Query("i") imdbID: String): Single<MovieDetail>


}