package com.example.mvvm_drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_drinks.AppDataBase

import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.data.model.DataSource
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieEntity
import com.example.mvvm_drinks.domain.RepositoryImpl
import com.example.mvvm_drinks.ui.ViewModel.MainAdapter
import com.example.mvvm_drinks.ui.ViewModel.MainViewModel
import com.example.mvvm_drinks.ui.ViewModel.VMFactory
import com.example.mvvm_drinks.vo.Resource
import kotlinx.android.synthetic.main.fragment_favorites.*
import java.text.FieldPosition

class FavoritesFragment : Fragment(), MainAdapter.OnMoviewClickListener {

    val viewModel by activityViewModels<MainViewModel>{ VMFactory(
        RepositoryImpl(
            DataSource(
                AppDataBase.getDataBase(requireActivity().applicationContext))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setupObersver()
    }
    private fun setupObersver(){
        viewModel.getFavoritesMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.d("Favorites_Movies", "${result.data}")
                    val movieList = result.data.map { result ->
                        Movie(result.movieId, result.imageMoviePoster, result.imageMovie, result.name, result.description)
                    }
                    rv_favorites_movies.adapter = MainAdapter(requireContext(),movieList, this)
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        rv_favorites_movies.layoutManager = LinearLayoutManager(requireContext())
        rv_favorites_movies.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onMovieClick(movie: Movie, position: Int) {
        Toast.makeText(requireContext(), "Movie Deleted from favorites", Toast.LENGTH_SHORT).show()
        val movie = MovieEntity(movie.movieId, movie.imageMovie, movie.imageMoviePoster, movie.name, movie.description)
        viewModel.deleteFavoriteMovie(movie)
        rv_favorites_movies.adapter?.notifyItemRemoved(position)
        rv_favorites_movies.adapter?.notifyItemRangeRemoved(position, rv_favorites_movies.adapter?.itemCount!!)
    }
}

