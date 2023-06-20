package com.lobo.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Обработка нажатий на кнопки с видами спорта
        val footballButton = rootView.findViewById<ImageView>(R.id.football_button)
        val hockeyButton = rootView.findViewById<ImageView>(R.id.hockey_button)
        val basketballButton = rootView.findViewById<ImageView>(R.id.basketball_button)

        footballButton.setOnClickListener {
            openAllMatchesFragment("Футбол")
        }

        hockeyButton.setOnClickListener {
            openAllMatchesFragment("Хоккей")
        }

        basketballButton.setOnClickListener {
            openAllMatchesFragment("Баскетбол")
        }

        return rootView
    }

    private fun openAllMatchesFragment(sportName: String) {
        val allMatchesFragment = AllMatchesFragment.newInstance(sportName)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, allMatchesFragment)
            .addToBackStack(null)
            .commit()
    }
}