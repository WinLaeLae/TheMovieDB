package mm.com.fairway.themoviedb.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.model.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel:ViewModel() {
    private var popularResult:MutableLiveData<MovieList> = MutableLiveData()
   fun setLoadResult(){
       var apiClient= ApiClient()
       var apiCall = apiClient.getPopularList(ApiClient.api_Key,ApiClient.language,ApiClient.page)
   apiCall.enqueue(object : Callback<MovieList>{
       override fun onFailure(call: Call<MovieList>, t: Throwable) {
           Log.d("Tag>>>>>",t.toString())
       }

       override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
           popularResult.value=response.body()
       }

   })
   }
    fun  getLoadResult():LiveData<MovieList> = popularResult

}