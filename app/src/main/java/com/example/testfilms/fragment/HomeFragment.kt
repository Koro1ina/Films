package com.example.testfilms.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.testfilms.R
import com.example.testfilms.adapter.FilmsAdapter
import com.example.testfilms.model.FilmsJSONModel
import com.example.testfilms.model.FilmsModel

import org.json.JSONObject


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.listFilmsView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        //взять нужную информацию с сервера
        val url = "https://imdb-api.com/ru/API/MostPopularMovies/k_d3b92uhc"
        val queue = Volley.newRequestQueue(context)
        var data = ArrayList<FilmsModel>()
        val request = StringRequest(Request.Method.GET, url, { result ->
            val json = JSONObject(result)
            val list = ArrayList<FilmsJSONModel>()
            val array = json.getJSONArray("items")

            for(i in 0 until array.length()){
                val film = array[i] as JSONObject
                val item = FilmsJSONModel(
                    film.getString("id"),
                    film.getString("image"),
                    film.getString("title"),
                    film.getString("imDbRating"))
                list.add(item)

            }
            for(i in 0 until list.size step 2){
                if(list.size != 0 ){
                    data.add(FilmsModel(list[i].id,
                        list[i].image,
                        list[i].title,
                        list[i].imDbRating,
                        list[i+1].id,
                        list[i+1].image,
                        list[i+1].title,
                        list[i+1].imDbRating))
                }
            }
            Log.d("Films", list.toString())
            recyclerView.adapter = FilmsAdapter(data)
        }, { error ->
            Log.d("Log", "Error: $error")
        }

        )
        queue.add(request)



    }



}



