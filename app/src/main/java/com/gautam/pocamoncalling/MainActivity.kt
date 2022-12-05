 package com.gautam.pocamoncalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gautam.json_passing.Adepter.RecyclerAdepter
import com.gautam.json_passing.Network.ApiClint
import com.gautam.pocamoncalling.databinding.ActivityMainBinding
import com.gautam.pokemonapi.Data.CallBackresponce
import com.gautam.pokemonapi.Data.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity  : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
     lateinit var adapter: RecyclerAdepter
     var isScroling=false
      var currentitems=0
     var totaleitems=0
     var scrollOutitems=0
     var offset =0
     var limit =20


     private  var userlist:MutableList<Results> = mutableListOf()


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         adapter= RecyclerAdepter(this, userlist)
            val manager=  LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

         binding.recycler.layoutManager=manager
         binding.recycler.adapter=adapter


         ApiClint.init().getUserList(offset,limit).enqueue(object :Callback<CallBackresponce>{
             override fun onResponse(
                 call: Call<CallBackresponce>,
                 response: Response<CallBackresponce>
             ) {
                 if (response.isSuccessful){
                     var res=response.body()
                     Log.e("TAG", "onResponse:$res ", )
                     res?.let {

                         adapter.setiteam(it.results)
                     }
                 }
             }

             override fun onFailure(call: Call<CallBackresponce>, t: Throwable) {
                 Log.e("TAG", "onResponse:${t.message} ", )
             }
         })

         binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                 super.onScrollStateChanged(recyclerView, newState)
                 if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                     isScroling=true

                 }

             }

             override  fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 super.onScrolled(recyclerView, dx, dy)
                    currentitems=manager.childCount
                    totaleitems=manager.itemCount
                    scrollOutitems=manager.findFirstVisibleItemPosition()

                 if (isScroling && (currentitems+scrollOutitems==totaleitems)){
                     isScroling=false
                     offset +=20
                     Log.e("TAG", "onScrolled: ", )
                     getData(offset,limit)
                 }

             }


         })


     }

     private fun getData(offset: Int, limit: Int) {

         ApiClint.init().getUserList(offset,limit).enqueue(object :Callback<CallBackresponce>{
             override fun onResponse(
                 call: Call<CallBackresponce>,
                 response: Response<CallBackresponce>
             ) {
                 if (response.isSuccessful){
                     var res=response.body()
                     Log.e("TAG", "onResponse:$res ", )
                     res?.let {

                         adapter.setiteam(it.results)
                     }
                 }
             }

             override fun onFailure(call: Call<CallBackresponce>, t: Throwable) {
                 Log.e("TAG", "onResponse:${t.message} ", )
             }
         })


     }


 }