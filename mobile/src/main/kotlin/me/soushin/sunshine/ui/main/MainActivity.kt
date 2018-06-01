package me.soushin.sunshine.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.AndroidInjection
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.AbstractActivity
import me.soushin.sunshine.ui.home.HomeFragment

class MainActivity : AbstractActivity() {

    val navController: NavController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        findViewById<Toolbar>(R.id.toolbar).also {
            setSupportActionBar(it)
            setupActionBarWithNavController(navController)
        }

        findViewById<BottomNavigationView>(R.id.bottomNavigation)?.apply {
            setupWithNavControllerEx(navController)
        }
        setContentFragment(R.id.navHostFragment) { HomeFragment.newInstance() }
    }
}
