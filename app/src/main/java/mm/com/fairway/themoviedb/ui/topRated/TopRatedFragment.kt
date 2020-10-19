package mm.com.fairway.themoviedb.ui.topRated

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
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_top_rated.*
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.adapter.TopRatedAdapter
import mm.com.fairway.themoviedb.model.Result
import mm.com.fairway.themoviedb.model.MovieList

class TopRatedFragment : Fragment(),TopRatedAdapter.ClickListener {
private lateinit var  topRatedAdapter:TopRatedAdapter

    private lateinit var topRatedViewModel: TopRatedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_top_rated,container,false)
    }

    override fun onResume() {
        super.onResume()
        topRatedViewModel.setLoadResult()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedViewModel= ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedAdapter= TopRatedAdapter()
        recylerView_top_rated.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter=topRatedAdapter
        }
        topRatedAdapter.setOnClickListener(this)
        observeTopRated()
    }

    private fun observeTopRated() {
      topRatedViewModel.getLoadResult().observe(viewLifecycleOwner, Observer<MovieList> {top ->
          topRatedAdapter.updateTopRatedResult(
              top.results as ArrayList<Result>
          )
          Log.d("New>>", top.results.get(0).toString())

      })
    }

    override fun OnClick(result: Result) {
        Log.d("Detail>>>Tag>>>>", result.id.toString())
        findNavController().navigate(TopRatedFragmentDirections.actionNavTopRatedToDetailMovieFragment(result.id))
    }
}