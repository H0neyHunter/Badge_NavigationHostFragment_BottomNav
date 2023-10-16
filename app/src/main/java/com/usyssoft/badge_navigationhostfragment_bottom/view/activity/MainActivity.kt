package com.usyssoft.badge_navigationhostfragment_bottom.view.activity

import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.badge.BadgeDrawable
import com.usyssoft.badge_navigationhostfragment_bottom.R
import com.usyssoft.badge_navigationhostfragment_bottom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var context: MainActivity
    private lateinit var b : ActivityMainBinding

    private val badge : BadgeDrawable
        get() = b.bottomNavigationView.getOrCreateBadge(R.id.badgeItem)

    private lateinit var bottomBadgeItem : MenuItem


    //private val containerID = R.id.fragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        context = this@MainActivity


        b.apply {
            val navhost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView,navhost.navController)

            bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.itemplus -> {
                        //findNavController(containerID).navigate(R.id.plusFragment)
                        navhost.navController.navigate(R.id.plusFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.itemminus -> {
                        //findNavController(containerID).navigate(R.id.minusFragment)
                        navhost.navController.navigate(R.id.minusFragment)
                        return@setOnItemSelectedListener true
                    }
                    else -> return@setOnItemSelectedListener false
                }
            }
            bottomBadgeItem = b.bottomNavigationView.menu.findItem(R.id.badgeItem)

            badgeFunction(false,0,R.color.black,R.color.white,null)


        }
    }

    fun badgeFunction(badgeVisible:Boolean,badgeNumber:Int,backgroundcolor:Int,textcolor:Int,bottomItemText:String?=null,bottomItemColor:Int?= R.color.black) {
        badge.isVisible = badgeVisible
        badge.number = badgeNumber
        badge.backgroundColor = ContextCompat.getColor(context,backgroundcolor)
        badge.badgeTextColor = ContextCompat.getColor(context,textcolor)


        //Badge değişince bottombar item text de değişilmesi istenilirse.
        if (bottomItemText != null) {
            bottomBadgeItem.title = bottomItemText

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                bottomBadgeItem.iconTintList = ColorStateList.valueOf(ContextCompat.getColor(context,
                    bottomItemColor!!
                ))
            }else {
                DrawableCompat.setTintList(DrawableCompat.wrap(bottomBadgeItem.icon!!), ColorStateList.valueOf(ContextCompat.getColor(context, bottomItemColor!!)))
            }
        }else {
            bottomBadgeItem.title = ""
        }

    }
}