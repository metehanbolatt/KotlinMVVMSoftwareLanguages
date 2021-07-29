package com.metehanbolat.kotlinmvvmsoftwarelanguages.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.metehanbolat.kotlinmvvmsoftwarelanguages.databinding.FragmentDetailsBinding
import com.metehanbolat.kotlinmvvmsoftwarelanguages.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailsViewModel
    private var languagesUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.getDataFromRoom()


        observeLiveData()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLiveData(){
        viewModel.languagesLiveData.observe(viewLifecycleOwner, { language ->
            language?.let {
                binding.languagesNameDetails.text = language.languagesNameModel
                binding.languagesDescriptionDetails.text = language.languagesDescriptionModel

            }
        })
    }

}