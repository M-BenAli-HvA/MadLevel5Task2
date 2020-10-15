package com.example.madlevel5task2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.LayoutDirection
import android.util.LayoutDirection.RTL
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Add Game"
        menu.removeItem(R.id.clear_game_backlog)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_AddGameFragment_to_GameBacklogFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}