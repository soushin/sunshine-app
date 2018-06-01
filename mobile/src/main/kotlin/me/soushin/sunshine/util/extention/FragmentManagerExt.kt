package me.soushin.sunshine.util.extention

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


fun FragmentManager.replaceFragment(containerViewId: Int, f: () -> Fragment) {
    f().apply {
        beginTransaction().replace(containerViewId, this).commit()
    }
}
