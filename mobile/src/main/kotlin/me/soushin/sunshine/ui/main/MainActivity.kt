package me.soushin.sunshine.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.AbstractActivity
import me.soushin.sunshine.ui.forecast.ForecastsFragment
import me.soushin.sunshine.ui.home.HomeFragment
import me.soushin.sunshine.ui.settings.SettingsActivity
import me.soushin.sunshine.util.extention.replaceFragment

class MainActivity : AbstractActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                SettingsActivity.startActivity(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        findViewById<Toolbar>(R.id.toolbar)?.let {
            setSupportActionBar(it)
        }

        findViewById<BottomNavigationView>(R.id.bottomNavigation)?.let {
            it.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> {
                        supportFragmentManager.replaceFragment(R.id.mainLayout) { HomeFragment.newInstance() }
                    }
                    R.id.nav_forecasts -> {
                        supportFragmentManager.replaceFragment(R.id.mainLayout) { ForecastsFragment.newInstance() }
                    }
                }
                true
            }
        }
        setContentFragment(R.id.mainLayout) { HomeFragment.newInstance() }
    }
}
