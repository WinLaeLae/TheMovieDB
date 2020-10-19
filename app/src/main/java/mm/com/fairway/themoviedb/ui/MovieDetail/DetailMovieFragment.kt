package mm.com.fairway.themoviedb.ui.MovieDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.adapter.ActorsAdapter
import mm.com.fairway.themoviedb.adapter.SimilarMovieAdapter
import mm.com.fairway.themoviedb.model.MovieList
import mm.com.fairway.themoviedb.model.Result
import mm.com.fairway.themoviedb.model.actors.ActorsList
import mm.com.fairway.themoviedb.model.actors.Cast
import mm.com.fairway.themoviedb.model.movieDetail.MovieID


class DetailMovieFragment : Fragment(), SimilarMovieAdapter.ClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    private var detailViewModel = DetailMovieViewModel()
    private lateinit var similarAdapter: SimilarMovieAdapter
    private lateinit var recommenAdapter: SimilarMovieAdapter
    private lateinit var actorAdapter: ActorsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        similarAdapter = SimilarMovieAdapter()
        recommenAdapter = SimilarMovieAdapter()
        actorAdapter = ActorsAdapter()
        var dataItem = arguments?.let {
            DetailMovieFragmentArgs.fromBundle(it)
        }
        var id = dataItem!!.movieID
        Log.d("Id>>", id.toString())

        detailViewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        detailViewModel.setLoadResult(id)
        observeDetailViewModel()

        detailViewModel.setActorLoad(id)

        detailViewModel.setSimilarLoad(id)
        detailViewModel.setRecommendationLoad(id)
        observeMovieList()
        recylerview_actor.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = actorAdapter
        }
        recylerview_similar.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = similarAdapter
        }
        recylerview_recommendation.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = recommenAdapter
        }

      //  similarAdapter.setOnClickListener(this)
     //   recommenAdapter.setOnClickListener(this)


    }

    fun observeDetailViewModel() {
        detailViewModel.getLoadResult().observe(
            viewLifecycleOwner,
            Observer<MovieID> { movieId -> // will get arryalist so no need to update
                Log.d("detail", movieId.toString())
                movieTitle_txt.text = movieId.title
                date_txt.text = movieId.release_date
                overView_txt.text = movieId.overview
                Picasso.get().load(ApiClient.imageUrl + movieId.backdrop_path)
                    .placeholder(R.drawable.bg_image).into(detail_img)
            }
        )

    }

    fun observeMovieList() {
        detailViewModel.getActorLoad().observe(viewLifecycleOwner, Observer<ActorsList> { movie ->

            actorAdapter.updateMovie(movie as ArrayList<Cast>)
        })
        detailViewModel.getSimilarLoad().observe(viewLifecycleOwner, Observer<MovieList> { movie ->
            similarAdapter.updateMovie(movie as ArrayList<Result>)
        })
        detailViewModel.getRecommendationLoad()
            .observe(viewLifecycleOwner, Observer<MovieList> { movie ->
                recommenAdapter.updateMovie(movie as ArrayList<Result>)
            })

    }


    override fun OnClick(result: Result) {

        findNavController().navigate(
            DetailMovieFragmentDirections.actionDetailMovieFragmentSelf(
                result.id
            )
        )
    }

}