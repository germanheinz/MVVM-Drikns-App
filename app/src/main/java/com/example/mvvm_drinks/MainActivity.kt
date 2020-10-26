package com.example.mvvm_drinks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

// TODO - NAV - 1 - CREATE IN RES A NEW "ANDROID RESOURCE FILE" WITH VALUE NAVIGATION
// TODO - NAV - 2 - CREATE THE FRAGMENT TO NAVIGATE BETWEEN THEM
// TODO - NAV - 3 - ADD IN ACTIVITY MAIN A FRAGMENTCONTAINERVIEW
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO - NAV - 7 - OVERRIDE THIS METHOD AND DEFINE NAVCONTROLLER
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // BACK ARROW
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    // TODO - NAV - 6 - OVERRIDE THIS METHOD
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }



}
