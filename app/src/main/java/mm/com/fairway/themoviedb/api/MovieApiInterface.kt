package mm.com.fairway.movie.api

import mm.com.fairway.themoviedb.model.MovieList
import mm.com.fairway.themoviedb.model.actors.ActorsList
import mm.com.fairway.themoviedb.model.movieDetail.MovieID
import mm.com.fairway.themoviedb.model.nowplay.NowPlayingMovie
import mm.com.fairway.themoviedb.model.person.PersonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("popular")
    fun getPopularMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>


    @GET("now_playing")
    fun getNowPlayingList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int

    ): Call<NowPlayingMovie>

    @GET("top_rated")
    fun getTopRatedList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int

    ): Call<MovieList>

    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String

    ): Call<MovieID>

    @GET("{movie_id}/credits")
    fun getActors(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String
    ): Call<ActorsList>

    @GET("{movie_id}/similar")
    fun getSimilars(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>

    @GET("{movie_id}/recommendations")
    fun getRecommendations(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>

    @GET("{person_id}")
    fun getBiography(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<PersonDetail>

}