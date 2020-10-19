package mm.com.fairway.themoviedb.ui.MovieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.model.MovieList
import mm.com.fairway.themoviedb.model.actors.ActorsList
import mm.com.fairway.themoviedb.model.movieDetail.MovieID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel : ViewModel() {
    private var result: MutableLiveData<MovieID> = MutableLiveData()
    private var similarResult: MutableLiveData<MovieList> = MutableLiveData()
    private  var recommenResult: MutableLiveData<MovieList> = MutableLiveData()
    private var actorResult: MutableLiveData<ActorsList> = MutableLiveData()
    var apiClient = ApiClient()
  //  var ID = 0
    lateinit var apiCallActior: Call<ActorsList>
    lateinit var apiCallList: Call<MovieList>
    fun getLoadResult(): LiveData<MovieID> = result
    fun setLoadResult(id: Int) {
      //  ID = id
        //  var movie_id=id
        Log.d("detail", id.toString())
        var apiCall = apiClient.getDetail(id, ApiClient.api_Key, ApiClient.language)
        apiCall.enqueue(object : Callback<MovieID> {
            override fun onFailure(call: Call<MovieID>, t: Throwable) {
                Log.d("TAG>>>>", t.toString())
            }

            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {
                Log.d("TAG>>>>", response.body().toString())
                result.value = response.body()
            }

        })
    }

    fun setSimilarLoad(id : Int) {
        apiCallList =
            apiClient.getSimilars(id, ApiClient.api_Key, ApiClient.language, ApiClient.page)
        apiCallList.enqueue(object : Callback<MovieList> {
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("TAG>>>>", t.toString())
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                similarResult.value = response.body()
            }

        })

    }

    fun getSimilarLoad(): MutableLiveData<MovieList> = similarResult
    fun getRecommendationLoad(): MutableLiveData<MovieList> = recommenResult
    fun setRecommendationLoad(id: Int) {
        apiCallList = apiClient.getRecommendationMovieList(
            id,
            ApiClient.api_Key,
            ApiClient.language,
            ApiClient.page
        )
        apiCallList.enqueue(object : Callback<MovieList> {
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("TAG>>>>", t.toString())
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                recommenResult.value = response.body()
            }

        })

    }

    fun setActorLoad(id: Int) {
        apiCallActior = apiClient.getActors(id, ApiClient.api_Key)
        apiCallActior.enqueue(object : Callback<ActorsList> {
            override fun onFailure(call: Call<ActorsList>, t: Throwable) {
                Log.d("TAG>>>>", t.toString())
            }

            override fun onResponse(call: Call<ActorsList>, response: Response<ActorsList>) {
                actorResult.value = response.body()
            }

        })
    }

    fun getActorLoad(): MutableLiveData<ActorsList> = actorResult


}