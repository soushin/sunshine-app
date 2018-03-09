package me.soushin.sunshine.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.AbstractActivity
import me.soushin.sunshine.ui.forecasts.ForecastsFragment
import me.soushin.sunshine.ui.settings.SettingsActivity

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
        setContentView(R.layout.activity_main)
        setContentFragment(R.id.mainLayout) { ForecastsFragment.newInstance() }
    }
}
