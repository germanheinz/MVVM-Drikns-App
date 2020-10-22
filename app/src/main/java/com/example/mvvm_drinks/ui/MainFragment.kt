package com.example.mvvm_drinks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mvvm_drinks.R
import com.example.mvvm_drinks.data.model.DataSource
import com.example.mvvm_drinks.domain.RepositoryImpl
import com.example.mvvm_drinks.ui.ViewModel.MainAdapter
import com.example.mvvm_drinks.ui.ViewModel.MainViewModel
import com.example.mvvm_drinks.ui.ViewModel.VMFactory
import com.example.mvvm_drinks.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {


    val viewModel by viewModels<MainViewModel>{ VMFactory(RepositoryImpl(DataSource())) }

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

//        buttonToGoDetail.setOnClickListener {
//            findNavController().navigate(R.id.detailMovie)
//        }
        setUpRecyclerView()
        viewModel.fetchMovieList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_main.adapter = MainAdapter(requireContext(), result.data)
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
}
