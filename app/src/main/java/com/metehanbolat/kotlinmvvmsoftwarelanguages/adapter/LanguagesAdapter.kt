package com.metehanbolat.kotlinmvvmsoftwarelanguages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.metehanbolat.kotlinmvvmsoftwarelanguages.databinding.ListLanguagesRowBinding
import com.metehanbolat.kotlinmvvmsoftwarelanguages.model.Languages
import com.metehanbolat.kotlinmvvmsoftwarelanguages.util.downloadFromGlide
import com.metehanbolat.kotlinmvvmsoftwarelanguages.util.placeHolderProgressBar
import com.metehanbolat.kotlinmvvmsoftwarelanguages.view.LanguagesFragmentDirections

class LanguagesAdapter(val languagesList: ArrayList<Languages>): RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder>() {

    class LanguagesViewHolder(val binding: ListLanguagesRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {
        val binding = ListLanguagesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LanguagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguagesViewHolder, position: Int) {
        holder.binding.languagesNameRow.text = languagesList[position].languagesNameModel
        holder.binding.languagesDescriptionRow.text = languagesList[position].languagesDescriptionModel

        holder.itemView.setOnClickListener {
            val action = LanguagesFragmentDirections.actionLanguagesFragmentToDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageViewRow.downloadFromGlide(languagesList[position].imageUrlModel, placeHolderProgressBar(holder.binding.root.context))
    }

    override fun getItemCount(): Int {
        return languagesList.size
    }

    fun updateLanguagesList(newLanguagesList: List<Languages>){
        languagesList.clear()
        languagesList.addAll(newLanguagesList)
        notifyDataSetChanged()
    }
}