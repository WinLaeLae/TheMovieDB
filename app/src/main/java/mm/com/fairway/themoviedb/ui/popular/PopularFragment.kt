package mm.com.fairway.themoviedb.ui.popular

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
import kotlinx.android.synthetic.main.fragment_popular.*
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.adapter.PopularAdapter
import mm.com.fairway.themoviedb.model.MovieList

import mm.com.fairway.themoviedb.model.Result

class PopularFragment : Fragment(), PopularAdapter.ClickListener {

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onResume() {
        super.onResume()
        popularViewModel.setLoadResult()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularAdapter = PopularAdapter()
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)

        recylerView_popular.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = popularAdapter
        }
        popularAdapter.setOnClickListener(this)
        observePopular()

    }

    private fun observePopular() {
        popularViewModel.getLoadResult()
            .observe(viewLifecycleOwner, Observer<MovieList> { popular ->
                popularAdapter.updatePopularResult(
                    popular.results as ArrayList<Result>
                )
                Log.d("New>>", popular.results.get(0).toString())
            })
    }

    override fun OnClick(result: Result) {
        Log.d("Detail>>>Tag>>>>", result.id.toString())
        findNavController().navigate(
            PopularFragmentDirections.actionNavPopularToDetailMovieFragment(
                result.id
            )
        )

    }
}
