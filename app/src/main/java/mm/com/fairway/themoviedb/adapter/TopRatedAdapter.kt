package mm.com.fairway.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_detail_movie.view.movieTitle_txt
import kotlinx.android.synthetic.main.item_movielist_layout.view.*
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.model.Result


class TopRatedAdapter(var topRatedList: ArrayList<Result> = ArrayList()): RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {
    var mClickListener: ClickListener? = null
    fun updateTopRatedResult(resultList:ArrayList<Result>){
        this.topRatedList=resultList
        notifyDataSetChanged()
    }
    fun setOnClickListener(clickListener: ClickListener){
        this.mClickListener= clickListener
    }

inner class TopRatedViewHolder(itemview: View):RecyclerView.ViewHolder(itemview),View.OnClickListener{

    lateinit var result:Result
    init {
          itemview.setOnClickListener(this)
      }
        fun bind(result:Result){
            this.result = result
            //itemView.movieTitle_txt.text= result.title
          //  itemView.relate_date_txt.text= result.release_date
            Picasso.get().load(ApiClient.imageUrl+result.poster_path).placeholder(R.drawable.bg_image)
                .into(itemView.Home_imageView)
        }

    override fun onClick(p0: View?) {
        mClickListener?.OnClick(result)
    }
}

interface ClickListener{
    fun OnClick(result:Result)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
       return  TopRatedViewHolder(LayoutInflater.from(parent.context)
           .inflate(R.layout.item_movielist_layout,parent,false))

    }

    override fun getItemCount(): Int {
        return topRatedList.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(topRatedList.get(position))
    }

}