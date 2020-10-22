package com.example.mvvm_drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.data.model.Movie


class DetailMovieFragment : Fragment() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movie = it.getParcelable<Movie>("movie")!!

            // UTIL, ADDING LOG TO VERIFY VALUE
            Log.d("MOVIE_DETAIL", "$movie ")
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }
}
