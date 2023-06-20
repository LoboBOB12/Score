package com.lobo.score


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView.setOnNavigationItemSelectedListener(navItemSelectedListener)

        // По умолчанию открываем раздел "Home"
        val homeFragment = HomeFragment()
        openFragment(homeFragment)
    }

    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val homeFragment = HomeFragment()
                    openFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_all_matches -> {
                    val allMatchesFragment = AllMatchesFragment()
                    openFragment(allMatchesFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_live_matches -> {
                    val liveMatchesFragment = LiveMatchesFragment()
                    openFragment(liveMatchesFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_support -> {
                    val supportFragment = SupportFragment()
                    openFragment(supportFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(null).commit()
    }
}