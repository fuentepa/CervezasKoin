package com.paf.cervezaskoin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.paf.cervezaskoin.R
import com.paf.cervezaskoin.databinding.FragmentBeersBinding
import com.paf.cervezaskoin.ui.common.UIState.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class BeersFragment : Fragment() {

    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!
    val vm: BeersFragmentViewModel by viewModel()
    private lateinit var adapter: BeersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BeersAdapter(::onBeerClicked)
        binding.recycler.adapter = adapter
        setupViewModelObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBeerClicked(id: Int) {
        val action = BeersFragmentDirections.actionBeersFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun setupViewModelObservers(){
        lifecycleScope.launchWhenCreated {
            vm.status.collect {
                when (it){
                    is EMPTY -> adapter.beers = emptyList()
                    is LOADING -> Unit
                    is ERROR -> Unit
                    is SUCCESS -> adapter.beers = it.data
                }
            }
        }
    }
}