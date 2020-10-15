package com.example.madlevel5task2.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.adapters.GameAdapter
import com.example.madlevel5task2.models.Game
import kotlinx.android.synthetic.main.fragment_game_backlog.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklogFragment : Fragment() {

    private val games = arrayListOf<Game>(
        Game(
            "Dragonball Z Kakarot", "PC, Playstation 4," +
                    "XBOX ONE", Date(), 0
        ),
        Game(
            "Dragonball Z Xenoverse 2", "PC, Playstation 4," +
                    "XBOX ONE", Date(), 0
        )
    )
    private val gameAdapter: GameAdapter = GameAdapter(games)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Game Backlog"
        super.onPrepareOptionsMenu(menu)
    }


    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        var callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete: Game = games[position]
                games.remove(gameToDelete)
                gameAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_game_backlog -> {
                Log.i("MainActivity", "Clicked on clear games button")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}