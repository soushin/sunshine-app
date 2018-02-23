package me.soushin.sunshine.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.forecasts.ForecastsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment(R.id.mainLayout)
    }

    private fun setContentFragment(containerViewId: Int) {
        supportFragmentManager.let { manager ->
            manager.findFragmentById(containerViewId)?.let { return }
            ForecastsFragment.newInstance().apply {
                manager?.beginTransaction()?.add(containerViewId, this)?.commit()
            }
        }
    }
}
