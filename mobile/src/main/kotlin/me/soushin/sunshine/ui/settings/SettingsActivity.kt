package me.soushin.sunshine.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import dagger.android.AndroidInjection
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.AbstractActivity

class SettingsActivity : AbstractActivity() {

    companion object {
        fun startActivity(activity: Activity?) {
            activity?.startActivity(Intent(activity, SettingsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setContentFragment(R.id.settingLayout) { SettingsFragment.newInstance() }
    }
}
