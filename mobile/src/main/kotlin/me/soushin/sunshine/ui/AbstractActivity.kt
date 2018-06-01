package me.soushin.sunshine.ui

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.soushin.sunshine.R
import javax.inject.Inject

abstract class AbstractActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector

    fun setContentFragment(containerViewId: Int, f: () -> Fragment) {
        supportFragmentManager.let { manager ->
            manager.findFragmentById(containerViewId)?.let { return }
            f().apply {
                manager?.beginTransaction()?.add(containerViewId, this)?.commit()
            }
        }
    }
}
