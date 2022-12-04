package toto.isis.fr.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import toto.isis.fr.models.*
import toto.isis.fr.models.tmdb.*

interface TmdbApi {

    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key : String = Api.apiKey): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") api_key : String = Api.apiKey, @Query("query") query : String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key") api_key : String = Api.apiKey): TmdbTvResult

    @GET("search/tv")
    suspend fun searchSeries(@Query("api_key") api_key : String = Api.apiKey, @Query("query") query : String): TmdbTvResult

    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") api_key : String = Api.apiKey): TmdbPersonResult

    @GET("search/person")
    suspend fun searchActors(@Query("api_key") api_key : String = Api.apiKey, @Query("query") query : String): TmdbPersonResult

    @GET("movie/{id}")
    suspend fun movie(@Path("id") id : Int, @Query("api_key") api_key : String = Api.apiKey, @Query("append_to_response") append_to_response : String = "credits"): TmdbMovieFull

    @GET("tv/{id}")
    suspend fun serie(@Path("id") id : Int, @Query("api_key") api_key : String = Api.apiKey, @Query("append_to_response") append_to_response : String = "credits"): TmdbTvFull
}