package ir.zngis.yaraapplication.repository.remote

import android.app.Application
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EntityApiImpl @Inject constructor(private val entityService: EntityService, val application: Application) :
    EntityApi {

    override fun getMovies(): Single<List<Movie>> {
        return entityService.getMoives().map {
            it.Search
        }
    }

    override fun getMovieDetail(imdbID: String): Single<MovieDetail> = entityService.getMoiveDetail(imdbID)

}