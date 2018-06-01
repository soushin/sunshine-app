package me.soushin.sunshine.ui.main

import android.support.design.widget.BottomNavigationView
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun BottomNavigationView.setupWithNavControllerEx(navController: NavController) {
    this.setOnNavigationItemSelectedListener { item ->
        try {
            navController.navigate(item.getItemId(), null, NavOptions.Builder().build())
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    navController.addOnNavigatedListener { controller, destination ->
        val destinationId = destination.id
        val menu = this.getMenu()
        var h = 0
        val size = menu.size()
        while (h < size) {
            val item = menu.getItem(h)
            if (item.getItemId() == destinationId) {
                item.setChecked(true)
            }
            h++
        }
    }
}
