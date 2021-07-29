package com.metehanbolat.kotlinmvvmsoftwarelanguages.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.metehanbolat.kotlinmvvmsoftwarelanguages.adapter.LanguagesAdapter
import com.metehanbolat.kotlinmvvmsoftwarelanguages.databinding.FragmentLanguagesBinding
import com.metehanbolat.kotlinmvvmsoftwarelanguages.viewmodel.LanguagesViewModel

class LanguagesFragment : Fragment() {

    private var _binding: FragmentLanguagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LanguagesViewModel
    private lateinit var languagesAdapter: LanguagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguagesBinding.inflate(inflater, container, false)
        val view = binding.root

        languagesAdapter = LanguagesAdapter(arrayListOf())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LanguagesViewModel::class.java)
        viewModel.refreshData()

        binding.languagesList.layoutManager = LinearLayoutManager(context)
        binding.languagesList.adapter = languagesAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.languagesList.visibility = View.GONE
            binding.languagesError.visibility = View.GONE
            binding.languagesLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLiveData(){
        viewModel.languages.observe(viewLifecycleOwner, { languages ->
            languages?.let {
                binding.languagesList.visibility = View.VISIBLE
                languagesAdapter.updateLanguagesList(languages)
            }
        })

        viewModel.languagesError.observe(viewLifecycleOwner, { error ->
            error?.let {
                if (it){
                    binding.languagesError.visibility = View.VISIBLE
                }else{
                    binding.languagesError.visibility = View.GONE
                }
            }

        })

        viewModel.languagesLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if (it){
                    binding.languagesLoading.visibility = View.VISIBLE
                    binding.languagesList.visibility = View.GONE
                    binding.languagesError.visibility = View.GONE
                }else{
                    binding.languagesLoading.visibility = View.GONE
                }
            }
        })
    }
}