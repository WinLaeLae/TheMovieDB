package mm.com.fairway.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_layout.view.*
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.model.Result

class SimilarMovieAdapter() :
    //var movieList: ArrayList<Result> = ArrayList()

    RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {
private var movieList= emptyList<Result>()
    var mClickListener: ClickListener? = null

    interface ClickListener {
        fun OnClick(result: Result)
    }
    fun updateMovie(movieList: ArrayList<Result>){
        this.movieList=movieList
        notifyDataSetChanged()
    }
    fun setOnClickListener(clicklistener:ClickListener){
        this.mClickListener=clicklistener
    }

   inner class SimilarMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
lateinit var  result: Result
       init {
           itemView.setOnClickListener(this)
       }
        fun bind(result: Result) {
            this.result=result
          // itemView.title_txt.text = result.title
            Picasso.get().load(ApiClient.imageUrl + result.poster_path)
                .placeholder(R.drawable.bg_image).into(itemView.img_movie)

        }

        override fun onClick(p0: View?) {
           mClickListener?.OnClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        return SimilarMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
    }

}