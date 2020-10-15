package com.example.madlevel5task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.models.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>):
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val formatter =  SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

        fun databind(game: Game) {
            itemView.tvGameTitle.text = game.title
            itemView.tvGamePlatforms.text = game.platforms
            itemView.tvGameDate.text = "Release: " + formatter.format(game.date).toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.databind(games[position])
    }

}