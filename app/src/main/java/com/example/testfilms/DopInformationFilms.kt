package com.example.testfilms

import android.annotation.SuppressLint
import android.net.wifi.p2p.WifiP2pManager.ActionListener
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testfilms.adapter.DopInforFilmAdapter
import com.example.testfilms.adapter.FilmsAdapter
import com.example.testfilms.model.ActorJSONModel
import com.example.testfilms.model.DopInforFilmModel
import com.example.testfilms.model.FilmsModel
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DopInformationFilms : AppCompatActivity() {


    var id = FilmsAdapter.id
    var count = 0;

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dop_information_films)

        var image : ImageView = findViewById(R.id.image);
        var title : TextView = findViewById(R.id.title);
        var plot : TextView = findViewById(R.id.plot)
        var rating : TextView = findViewById(R.id.rating);
        var releaseDate : TextView = findViewById(R.id.releaseDate)
        var onVideo : Button = findViewById(R.id.trailer)
        var video : TextView = findViewById(R.id.video)
        var like : ImageButton = findViewById(R.id.like)


        like.setOnClickListener(View.OnClickListener {
            if (count==0){
                like.setImageResource(R.drawable.baseline_favorite_24)
                count = 1
            }else{
                like.setImageResource(R.drawable.baseline_favorite)
                count = 0
            }
        })

        //отображение ссылки на трейлер фильма
        onVideo.setOnClickListener(View.OnClickListener {
            onVideo.isEnabled = false
            if(video.visibility == View.INVISIBLE){
                val url = "https://imdb-api.com/ru/API/YouTubeTrailer/k_d3b92uhc/$id"
                val queue = Volley.newRequestQueue(this)
                val request = StringRequest(Request.Method.GET, url, { result ->
                    val json = JSONObject(result)
                     video.text = json.getString("videoUrl")
                    video.visibility =  View.VISIBLE
                }, { error ->
                    Log.d("Log", "Error: $error")
                }

                )
                queue.add(request)
            }else{
                video.visibility =  View.INVISIBLE
            }
            onVideo.isEnabled = true

        })
        val actors: RecyclerView = findViewById(R.id.actor)
        actors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val url = "https://imdb-api.com/ru/API/Title/k_d3b92uhc/$id/Ratings"
        val queue = Volley.newRequestQueue(this)
        var data = ArrayList<DopInforFilmModel>()
        val request = StringRequest(Request.Method.GET, url, { result ->
            val json = JSONObject(result)
            Picasso.get().load(json.getString("image")).into(image)
            title.text = json.getString("title")
            plot.text = json.getString("plot")
            rating.text = "Рейтинг ${json.getString("imDbRating")}"
            var date = LocalDate.parse(json.getString("releaseDate"))
            var formatter = DateTimeFormatter.ofPattern("dd MMMM  yyyy")
            var formattedDate = date.format(formatter)
            releaseDate.text = "Дата релиза: $formattedDate"

            val list = ArrayList<ActorJSONModel>()
            val array = json.getJSONArray("actorList")

            for(i in 0 until array.length()){
                val film = array[i] as JSONObject
                val item = ActorJSONModel(
                    film.getString("image"),
                    film.getString("name"),
                    film.getString("asCharacter"))
                list.add(item)

            }
            for(i in 0 until list.size step 2){
                if(list.size != 0 ){
                    data.add(
                        DopInforFilmModel(
                        list[i].image,
                        list[i].name,
                        list[i].asCharacter)
                    )
                }
            }
            Log.d("Films", list.toString())
            actors.adapter = DopInforFilmAdapter(data);
        }, { error ->
            Log.d("Log", "Error: $error")
        }

        )
        queue.add(request)

    }

}