package ir.zngis.yaraapplication.injection.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.zngis.yaraapplication.R
import ir.zngis.yaraapplication.injection.component.ViewModelSubComponent
import ir.zngis.yaraapplication.repository.local.Database.LocalDb
import ir.zngis.yaraapplication.repository.local.LocalRepository
import ir.zngis.yaraapplication.repository.local.LocalRepositoryImpl
import ir.zngis.yaraapplication.repository.local.dao.MovieDao
import ir.zngis.yaraapplication.repository.local.dao.MovieDetailDao
import ir.zngis.yaraapplication.repository.local.dao.RatingDao
import ir.zngis.yaraapplication.repository.remote.EntityApi
import ir.zngis.yaraapplication.repository.remote.EntityApiImpl
import ir.zngis.yaraapplication.repository.remote.EntityService
import ir.zngis.yaraapplication.use_case.MovieManager
import ir.zngis.yaraapplication.use_case.MovieManagerImpl
import ir.zngis.yaraapplication.vm.factory.ProjectViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(subcomponents = [ViewModelSubComponent::class])
class AppModule {


    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): LocalDb {

        return Room.databaseBuilder(application, LocalDb::class.java, LocalDb.DB_NAME).allowMainThreadQueries().build()
    }


    @Provides
    @Singleton
    fun providesMovieDao(localDb: LocalDb): MovieDao {
        return localDb.movieDao()
    }


    @Provides
    @Singleton
    fun providesMovieDetailDao(localDb: LocalDb): MovieDetailDao {
        return localDb.movieDetailDao()
    }


    @Provides
    @Singleton
    fun providesRatingDao(localDb: LocalDb): RatingDao {
        return localDb.ratingDao()
    }


    @Provides
    @Singleton
    fun providesTbClassSource(
        movieDao: MovieDao,
        movieDetailDao: MovieDetailDao,
        ratingDao: RatingDao
    ): LocalRepository {

        return LocalRepositoryImpl(
            movieDao, movieDetailDao, ratingDao
        )
    }


    @Provides
    @Singleton
    fun provideRetrofit(context: Application): Retrofit {

        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()

        val gsonBuilder = GsonBuilder()

        val myGson = gsonBuilder.create()

        return Retrofit.Builder().baseUrl(context.getString(R.string.baseUrl))
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(myGson))
            .build()

    }

    @Provides
    @Singleton
    fun provideEntityService(retrofit: Retrofit): EntityService {
        return retrofit.create(EntityService::class.java)
    }

    @Provides
    @Singleton
    fun provideEntityApi(entityService: EntityService, application: Application): EntityApi {
        return EntityApiImpl(entityService, application)
    }


    @Provides
    @Singleton
    fun provideMovieManager(
        localRepository: LocalRepository,
        remoteRepository: EntityApi
    ): MovieManager {
        return MovieManagerImpl(localRepository, remoteRepository)
    }


    @Singleton
    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {

        return ProjectViewModelFactory(viewModelSubComponent.build())
    }
}