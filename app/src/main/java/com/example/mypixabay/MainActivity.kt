package com.example.mypixabay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mypixabay.databinding.ActivityMainBinding
import com.example.mypixabay.model.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var page = 1
    var adapter = PhotoAdapter(arrayListOf())
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    binding.requestByImage(++page)
                }
            }
        })
    }

   private fun initClicker() {
        with(binding) {
            changePage.setOnClickListener {
                requestByImage(++page)
            }
            getImage.setOnClickListener {
                page = 1
                requestByImage(page)
            }
        }
    }

    private fun ActivityMainBinding.requestByImage(page: Int) {
        App.api.getImages(keyWord = binding.photoEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    response.body()?.hits?.let { listImageModel ->
                        listImageModel.forEach {
                            adapter.addImage(it)
                        }
                        binding.recyclerview.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure ${t.message}")
                }

            })
    }
}