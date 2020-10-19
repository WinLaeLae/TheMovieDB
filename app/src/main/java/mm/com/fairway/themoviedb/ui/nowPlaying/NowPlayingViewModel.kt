package mm.com.fairway.themoviedb.ui.nowPlaying

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.model.nowplay.NowPlayingMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel:ViewModel() {
    private var nowplayingResult:MutableLiveData<NowPlayingMovie> = MutableLiveData()
   fun setLoadResult(){
       var apiClient= ApiClient()
       var apiCall = apiClient.getNowPlayingList(ApiClient.api_Key,ApiClient.language,ApiClient.page)
   apiCall.enqueue(object : Callback<NowPlayingMovie>{
       override fun onFailure(call: Call<NowPlayingMovie>, t: Throwable) {
           Log.d("Tag>>>>>",t.toString())
       }

       override fun onResponse(call: Call<NowPlayingMovie>, response: Response<NowPlayingMovie>) {
           nowplayingResult.value=response.body()
       }

   })
   }
    fun  getLoadResult():LiveData<NowPlayingMovie> = nowplayingResult

}