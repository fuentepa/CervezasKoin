package com.paf.cervezaskoin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.paf.cervezaskoin.R
import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.databinding.FragmentBeerDetailBinding
import com.paf.cervezaskoin.ui.common.UIState
import com.paf.cervezaskoin.ui.common.loadUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailBeerFragment : Fragment() {

    private val args by navArgs<DetailBeerFragmentArgs>()
    val vm: DetailBeerFragmentViewModel by viewModel { parametersOf(args.id) }
    private var _binding: FragmentBeerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservers()

       /* binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_DetailFragment_to_BeersFragment)
        }
*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModelObservers(){
        lifecycleScope.launchWhenCreated {
            vm.status.collect {
                when (it){
                    is UIState.EMPTY -> Unit
                    is UIState.LOADING -> Unit
                    is UIState.ERROR -> Unit
                    is UIState.SUCCESS -> updateUI(it.data)
                }
            }
        }
    }

    private fun updateUI(beer: Beer) {
        with(binding) {
            movieDetailToolbar.title = beer.name
            beerImage.loadUrl(beer.imageUrl)
            beerDescription.text = beer.description
            availableFab.text = if (beer.available) getString(R.string.txt_available) else getString(R.string.txt_not_available)
            beerDetailInfo.setBeer(beer)
        }
    }
}