package com.fuze.ttapp.ui.tweetsearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.fuze.ttapp.R
import com.fuze.ttapp.api.models.Twit

import java.util.ArrayList

class TweetAdapter( internal var context: Context) :
    RecyclerView.Adapter<TweetAdapter.ViewHolder>() {
      var tweets: List<Twit> = mutableListOf()
       set(value) {
           field=value
          notifyDataSetChanged()
       }

    fun clear() {
        tweets = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.tweet_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (user, body) = tweets[position]
        holder.title.text = user!!.userName
        holder.screentitle.text = "@"+user!!.screenName
        holder.message.text = body
        holder.itemView.setOnClickListener{
            val intent = Intent(context, UserProfileActivity::class.java).apply {
                putExtra(USER, holder.screentitle.text.toString())
            }
            context.startActivity(intent)
        }
        Glide.with(context)
            .load(user.imageUrl)
            .centerCrop()
            .into(holder.imageView)


    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<View>(R.id.title) as TextView
        val screentitle: TextView = itemView.findViewById<View>(R.id.screentitle) as TextView
        val message: TextView = itemView.findViewById<View>(R.id.message) as TextView
        val imageView: ImageView = itemView.findViewById<View>(R.id.userImage) as ImageView
    }
}
