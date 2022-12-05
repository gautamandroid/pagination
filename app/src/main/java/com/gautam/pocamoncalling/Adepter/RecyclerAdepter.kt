package com.gautam.json_passing.Adepter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gautam.pocamoncalling.databinding.GreiedviewBinding
import com.gautam.pokemonapi.Data.Results



class RecyclerAdepter(var context: Context,var userlist:MutableList<Results>):RecyclerView.Adapter<RecyclerAdepter.MyViewHolder>(){


    class MyViewHolder(var binding: GreiedviewBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(GreiedviewBinding.inflate(LayoutInflater.from(context),parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var User=userlist[position ]
        holder.binding.tvName.text = "${User.name} "
        holder.binding.tvEmail.text = User.url
     /*   Glide.with(context)
            .load(User.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.hourglass)
            .into(holder.binding.ivThumbnail)*/
    }
    override fun getItemCount(): Int {
        return userlist.size
    }
    fun setiteam(newUserList: MutableList<Results>){
        userlist.addAll(newUserList)
        notifyDataSetChanged()  //to refresh recyclerview adapter

    }
}
