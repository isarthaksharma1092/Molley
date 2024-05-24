package com.isarthaksharma.melody

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class myDataAdaptor(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<myDataAdaptor.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_design,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        //returns the total number of items int the data set held by the adapter
        //thats all
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // called by Recycler View to display the data at the specified position
        // basically to populate the data into the view
        val currentData = dataList[position]
        //song name
        holder.title.text = currentData.title

        //artist name
        holder.artName.text = currentData.artist.name

        //album name
        holder.albName.text = currentData.album.title

        //song poster
        Picasso.get().load(currentData.album.cover).into(holder.imgMusic)

        //media control like pause/play
        val mediaPlayer = MediaPlayer.create(context,currentData.preview.toUri())
        holder.play.setOnClickListener{
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener{
            mediaPlayer.pause()
        }
    }
    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        // create the view in case the layout manager fails to create view for the data
        val imgMusic: ImageView
        val title: TextView
        val artName: TextView
        val albName: TextView
        val pause: ImageButton
        val play: ImageButton

        init {
            imgMusic = itemView.findViewById(R.id.imgMusic)
            title = itemView.findViewById(R.id.songName)
            artName = itemView.findViewById(R.id.artName)
            albName = itemView.findViewById(R.id.albName)
            pause = itemView.findViewById(R.id.pause)
            play = itemView.findViewById(R.id.play)

        }
    }
}