package com.example.mvvm_drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.mvvm_drinks.AppDataBase

import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.data.model.DataSourceImpl
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieEntity
import com.example.mvvm_drinks.domain.RepositoryImpl
import com.example.mvvm_drinks.ui.ViewModel.MainViewModel
import com.example.mvvm_drinks.ui.ViewModel.VMFactory
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class DetailMovieFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>{ VMFactory(RepositoryImpl(DataSourceImpl(
        AppDataBase.getDataBase(requireActivity().applicationContext)))) }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageMoviewUrl = "https://image.tmdb.org/t/p/w500/${movie.imageMovie}"
        val imageMoviePathwUrl = "https://image.tmdb.org/t/p/w500/${movie.imageMoviePoster}"

        Glide.with(requireContext()).load(imageMoviewUrl).into(imageMovieDetail)
        Glide.with(requireContext()).load(imageMoviePathwUrl).into(imagePosterDetail)

        movieNameDetail.text       = movie.name
        descritionMovieDetail.text = movie.description

        btn_like.setOnClickListener {
            viewModel.saveMovie(MovieEntity(movie.movieId, movie.imageMovie,movie.imageMoviePoster, movie.name, movie.description))
            Toast.makeText(requireContext(), "Saved in favorites!", Toast.LENGTH_SHORT).show()
        }

    }

}
