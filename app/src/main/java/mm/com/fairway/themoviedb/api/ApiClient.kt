package mm.com.fairway.movie.api


import mm.com.fairway.themoviedb.model.MovieList
import mm.com.fairway.themoviedb.model.actors.ActorsList
import mm.com.fairway.themoviedb.model.movieDetail.MovieID
import mm.com.fairway.themoviedb.model.nowplay.NowPlayingMovie
import mm.com.fairway.themoviedb.model.person.PersonDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ApiClient {
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val PERSON_Url = "https://api.themoviedb.org/3/person/"

    companion object {
        val api_Key = "76a2481b184330a7695211869897c6af"
        val language = "en-US"
        val page = 1
        var imageUrl = "https://image.tmdb.org/t/p/w500/"
    }

    var apiInterface: MovieApiInterface
    var apiInterfacePerson: MovieApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(MovieApiInterface::class.java)
        val retrofitPerson = Retrofit.Builder().baseUrl(PERSON_Url)
            .addConverterFactory(GsonConverterFactory.create()).build()
        apiInterfacePerson = retrofitPerson.create(MovieApiInterface::class.java)
    }

    fun getPopularList(
        api_Key: String,
        language: String,
        page: Int
    ): Call<MovieList> {
        return apiInterface.getPopularMovieList(api_Key, language, page)
    }

    fun getNowPlayingList(
        api_Key: String,
        language: String,
        page: Int
    ): Call<NowPlayingMovie> {
        return apiInterface.getNowPlayingList(api_Key, language, page)
    }

    fun getTopRatedList(
        api_Key: String,
        language: String,
        page: Int
    ): Call<MovieList> {
        return apiInterface.getTopRatedList(api_Key, language, page)
    }

    fun getDetail(
        movie_id: Int,
        api_Key: String,
        language: String
    ): Call<MovieID> {// to call fun from detailMovie
        return apiInterface.getDetail(movie_id, api_Key, language)

    }

    fun getSimilars(
        id: Int,
        api_key: String,
        language: String,
        page: Int
    ): Call<MovieList> {
        return apiInterface.getSimilars(id, api_key, language, page)
    }

    fun getActors(
        id: Int,
        api_key: String
    ): Call<ActorsList> {
        return apiInterface.getActors(id, api_key)
    }

    fun getBiography(
        id: Int,
        api_key: String,
        language: String
    ): Call<PersonDetail> {
        return apiInterfacePerson.getBiography(id, api_key, language)
    }
    fun getRecommendationMovieList(id: Int,
                                   api_key: String,
                                   language: String,
                                   page: Int):Call<MovieList>{
        return apiInterface.getSimilars(id, api_key, language, page)

    }

}

//https://api.themoviedb.org/3/movie/612706?api_key=76a2481b184330a7695211869897c6af&language=en-US
//https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1