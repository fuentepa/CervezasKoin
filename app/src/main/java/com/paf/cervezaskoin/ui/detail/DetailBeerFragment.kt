package com.paf.cervezaskoin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paf.cervezaskoin.R
import com.paf.cervezaskoin.databinding.FragmentBeerDetailBinding


class DetailBeerFragment : Fragment() {

    private var _binding: FragmentBeerDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
       /* binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_DetailFragment_to_BeersFragment)
        }
*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}