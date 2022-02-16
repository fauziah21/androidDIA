package com.digimaster.projectdia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digimaster.projectdia.databinding.ItemMovieBinding
import com.digimaster.projectdia.model.MovieModel

interface MovieClickListener{
    fun onClickItem(movieId : Int)
}

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var listMovie: List<MovieModel>? = null
    var context: Context? = null
    var movieClickListener: MovieClickListener? = null

    fun updateData(listMovie: List<MovieModel>?){
        this.listMovie = listMovie
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var position = adapterPosition
            var movieId = listMovie!![position].id
            movieClickListener?.onClickItem(movieId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            val data = listMovie!!.get(position)
            binding.tvTitle.text = data.title
            binding.tvOverview.text = data.overview
            Glide.with(context!!)
                .load("https://image.tmdb.org/t/p/original"+data.backdrop_path)
                .into(binding.ivMovie)

        }
    }

    override fun getItemCount(): Int {
        return listMovie?.size ?: 0
    }
}