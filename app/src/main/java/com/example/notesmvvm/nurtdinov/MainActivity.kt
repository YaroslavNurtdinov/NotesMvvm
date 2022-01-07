package com.example.notesmvvm.nurtdinov

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesmvvm.nurtdinov.databinding.ActivityMainBinding
import com.example.notesmvvm.nurtdinov.utilit.APP_ACTIVITY
import com.example.notesmvvm.nurtdinov.utilit.AppPreference

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mToolbar = mBinding.toolbar
        mToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolbar)
        setupActionBarWithNavController(navController)
        AppPreference.getPreference(this)
    }

       override fun onSupportNavigateUp(): Boolean {
           return navController.navigateUp() || super.onNavigateUp()
       }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}