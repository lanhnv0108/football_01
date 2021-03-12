package com.sun.uefascore.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import com.sun.uefascore.R
import com.sun.uefascore.screen.base.HomePageFragment
import com.sun.uefascore.screen.searchteam.SearchFragment
import com.sun.uefascore.utils.addFragment
import com.sun.uefascore.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomePageFragment.newInstance(), containerLayout.id)
        setFlags()
    }

    private fun setFlags() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }

    private var doubleBackPressed = false
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!doubleBackPressed) {
            this.doubleBackPressed = true
            Toast.makeText(this, "Back again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable { doubleBackPressed = false }, 2000)
            return
        } else {
            super.onBackPressed()
            return
        }
    }
}
