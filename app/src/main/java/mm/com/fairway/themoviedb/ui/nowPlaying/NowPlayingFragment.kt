package mm.com.fairway.themoviedb.ui.nowPlaying

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.adapter.NowPlayingAdapter
import mm.com.fairway.themoviedb.model.nowplay.NowPlayingMovie
import mm.com.fairway.themoviedb.model.nowplay.Result

class NowPlayingFragment : Fragment(),NowPlayingAdapter.ClickListener {

    private lateinit var nowPlayingViewModel:NowPlayingViewModel
private lateinit var  nowPlayingAdapter:NowPlayingAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_now_playing,container,false)
    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewModel.setLoadResult()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingViewModel= ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        nowPlayingAdapter= NowPlayingAdapter()
        recylerView_now_playing.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter=nowPlayingAdapter
        }
        nowPlayingAdapter.setOnClickListener(this)
        obserTopRated()
    }

    private fun obserTopRated() {
        nowPlayingViewModel.getLoadResult().observe(viewLifecycleOwner, Observer <NowPlayingMovie>{ nowplaying ->
            nowPlayingAdapter.updateNowPlayingResult(
                nowplaying.results as ArrayList<Result>
            )
            Log.d("New>>", nowplaying.results.get(0).toString())
        })
    }

    override fun OnClick(result: Result) {
       findNavController().navigate(NowPlayingFragmentDirections.actionNavNowPlayingToDetailMovieFragment(result.id))
    }
}