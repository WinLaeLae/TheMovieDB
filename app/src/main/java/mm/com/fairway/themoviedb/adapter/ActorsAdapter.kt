package mm.com.fairway.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_actors_layout.view.*
import kotlinx.android.synthetic.main.item_movie_layout.view.*
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.themoviedb.R
import mm.com.fairway.themoviedb.model.actors.Cast


class ActorsAdapter(var actorList: ArrayList<Cast>? = ArrayList()) :
    RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    var mClickListener: ClickListener? = null

    interface ClickListener {
        fun OnClick(result: Cast)
    }
    fun updateMovie(actorList: ArrayList<Cast>){
        this.actorList=actorList
        notifyDataSetChanged()
    }
    fun setOnClickListener(clicklistener:ClickListener){
        this.mClickListener=clicklistener
    }

   inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
lateinit var  actor:Cast
       init {
           itemView.setOnClickListener(this)
       }
        fun bind(actor:Cast) {
            this.actor=actor
            itemView.actor_name_txt.text = actor.name
            Picasso.get().load(ApiClient.imageUrl + actor.profile_path)
                .placeholder(R.drawable.bg_image).into(itemView.img_actor)

        }

        override fun onClick(p0: View?) {
           mClickListener?.OnClick(actor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_actors_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return actorList!!.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actorList!!.get(position))
    }

}