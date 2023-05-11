package com.example.testfilms.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testfilms.DopInformationFilms
import com.example.testfilms.R
import com.example.testfilms.model.DopInforFilmModel
import com.example.testfilms.model.FilmsModel
import com.squareup.picasso.Picasso

class DopInforFilmAdapter(private val listFilms: List<DopInforFilmModel>) : RecyclerView
.Adapter<DopInforFilmAdapter.ViewHolderFilms>() {


    class ViewHolderFilms(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val asCharacter: TextView = itemView.findViewById(R.id.asCharacter)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilms {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.actor_list, parent, false)
        return ViewHolderFilms(itemView)
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder: ViewHolderFilms, position: Int) {

        holder.name.text= listFilms[position].name
        holder.asCharacter.text= listFilms[position].asCharacter
        Picasso.get().load(listFilms[position].image).into(holder.image)


    }

    override fun getItemCount() = listFilms.size


}