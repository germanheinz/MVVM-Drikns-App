package com.example.mvvm_drinks.ui.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.base.BaseViewHolder
import com.example.mvvm_drinks.data.model.Movie
import kotlinx.android.synthetic.main.movie_row.view.*

class MainAdapter(private val context: Context, private val listOfMovies:List<Movie>, private val itemClickListener: OnMoviewClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMoviewClickListener{
        fun onMovieClick(movie: Movie)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(listOfMovies[position],position)
        }
    }

    // RETURN EACH ELEMENT OF THE LIST
    inner class MainViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView){
        override fun bind(movie: Movie, position: Int) {
//            Glide.with(context).load(movie.imageMovie).into(itemView.img_movie)
            itemView.movieName_row.text   = movie.name
            itemView.description_row.text = movie.description

            //ADDING CLICK LISTENER
            itemView.setOnClickListener { itemClickListener.onMovieClick(movie) }
        }

    }
}