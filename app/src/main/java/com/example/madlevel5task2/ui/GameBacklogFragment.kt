package com.example.madlevel5task2.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.adapters.GameAdapter
import com.example.madlevel5task2.models.Game
import com.example.madlevel5task2.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.fragment_game_backlog.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklogFragment : Fragment() {
    private lateinit var navController: NavController
    private val viewModel: GameViewModel by viewModels()

    private val games = arrayListOf<Game>()
    private val gameAdapter: GameAdapter = GameAdapter(games)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        navController = findNavController()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { view ->
            navController.navigate(R.id.action_GameBacklogFragment_to_AddGameFragment)
        }
        initViews()
        observeAddGameResult()
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

    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer {
            games ->
            this@GameBacklogFragment.games.clear()
            this@GameBacklogFragment.games.addAll(games)
            this@GameBacklogFragment.games.sortByDescending { game ->
                game.date
            }
            this.gameAdapter.notifyDataSetChanged()
        })
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
                viewModel.deleteGame(gameToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_game_backlog -> {
                viewModel.deleteAllGames()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}