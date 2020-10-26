package com.example.mvvm_drinks.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_drinks.AppDataBase
import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.data.model.DataSourceImpl
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.domain.RepositoryImpl
import com.example.mvvm_drinks.ui.ViewModel.MainAdapter
import com.example.mvvm_drinks.ui.ViewModel.MainViewModel
import com.example.mvvm_drinks.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

// IMPLEMENTING CLICK LISTENER OF ADAPTER
@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnMoviewClickListener {

    private val handler: Handler = Handler()
    private var runnable: Runnable? = null

//  INSTANCE VIEWMODEL WITH HILT
    private val viewModel by activityViewModels<MainViewModel>()

//  OLD INSTANCE OF VIEWMODEL
//    val viewModel by activityViewModels<MainViewModel>{ VMFactory(RepositoryImpl(DataSourceImpl(
//        AppDataBase.getDataBase(requireActivity().applicationContext)))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    // TODO - NAV - 4 - OVERRIDE METHOD TO ACCESS AT BUTTON AFTER ONCREATEVIEW
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpSearchView()
        setUpObserver()
        btn_go_favorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }


    }
    private fun setUpObserver(){
        viewModel.fetchMovieList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    // PASS CLICK LISTENER, AS A LAST PARAMETER (THIS)
                    rv_main.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }

    // METHOD TO SETUP RECYCLERVIEW
    private fun setUpRecyclerView(){
        rv_main.layoutManager = LinearLayoutManager(requireContext())
        rv_main.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onMovieClick(movie: Movie, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.action_mainFragment_to_detailMovie, bundle)
    }

    private fun setUpSearchView(){
        searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setMovieName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacks(runnable)
                runnable = Runnable { viewModel.setMovieName(newText!!) }
                handler.postDelayed(runnable, 500)

                return false
            }
        })
    }
}
