package com.example.testfilms.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.testfilms.DopInformationFilms
import com.example.testfilms.R
import com.example.testfilms.model.FilmsModel
import com.squareup.picasso.Picasso


class FilmsAdapter(private val listFilms: List<FilmsModel>) : RecyclerView
.Adapter<FilmsAdapter.ViewHolderFilms>() {


    var count = 0;
    var count1 = 0;


    class ViewHolderFilms(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image1)
        val image1: ImageView = itemView.findViewById(R.id.image2)
        val title: TextView = itemView.findViewById(R.id.title)
        val title1: TextView = itemView.findViewById(R.id.title1)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val rating1: TextView = itemView.findViewById(R.id.rating1)
        val film: LinearLayout = itemView.findViewById(R.id.film);
        val film1: LinearLayout = itemView.findViewById(R.id.film1);
        val like1: ImageButton = itemView.findViewById(R.id.like1)
        val like2: ImageButton = itemView.findViewById(R.id.like2)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilms {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_films, parent, false)
        return ViewHolderFilms(itemView)
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder: ViewHolderFilms, position: Int) {


                holder.title.text= listFilms[position].title1
                holder.rating.text= "Рейтинг: "+listFilms[position].rating1
                holder.title1.text= listFilms[position].title2
                holder.rating1.text= "Рейтинг: "+ listFilms[position].rating2
                Picasso.get().load(listFilms[position].images1).into(holder.image)
                Picasso.get().load(listFilms[position].images2).into(holder.image1)



                var id1 = listFilms[position].id1
                var id2 = listFilms[position].id2


        //изменение состояния кнопки "Избранное"
        holder.like1.setOnClickListener(View.OnClickListener {

            if (count==0){
                holder.like1.setImageResource(R.drawable.baseline_favorite_24)
                count = 1
            }else{
                holder.like1.setImageResource(R.drawable.baseline_favorite)
                count = 0
            }
        })

        holder.like2.setOnClickListener(View.OnClickListener {

            if (count1==0){
                holder.like2.setImageResource(R.drawable.baseline_favorite_24)
                count1 = 1
            }else{
                holder.like2.setImageResource(R.drawable.baseline_favorite)
                count1 = 0
            }
        })

        //переход на дополнительную информацию о фильме
        holder.film.setOnClickListener(View.OnClickListener {
            id = id1
            onTransitionDopInformationFilms(holder)

        })

        holder.film1.setOnClickListener(View.OnClickListener {
            id = id2
            onTransitionDopInformationFilms(holder)
        })

    }

    override fun getItemCount() = listFilms.size

    fun onTransitionDopInformationFilms(holder: ViewHolderFilms){
        holder.itemView.context.startActivity(Intent( holder.itemView.context, DopInformationFilms::class.java))


    }

    companion object{
        lateinit var id : String
    }


}


