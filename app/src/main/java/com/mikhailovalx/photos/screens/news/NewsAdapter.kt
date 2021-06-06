package com.mikhailovalx.photos.screens.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhailovalx.photos.R
import com.mikhailovalx.photos.data.news.News
import com.squareup.picasso.Picasso

class NewsAdapter(val news: MutableList<News> = mutableListOf()) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(news: News){
            Picasso.get()
                .load(news.urlToImage)
                .into(itemView.findViewById<ImageView>(R.id.img_news_item))

            itemView.findViewById<TextView>(R.id.title_news_item).text = news.title
        }
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int = news.size
}