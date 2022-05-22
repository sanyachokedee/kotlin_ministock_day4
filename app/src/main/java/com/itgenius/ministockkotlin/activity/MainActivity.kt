package com.itgenius.ministockkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.itgenius.ministockkotlin.R


class MainActivity : AppCompatActivity() {

    // การสร้างตัวแปรไว้สำหรับทำงานกับ View Binding
    lateinit var navController: NavController // ควบคุมตัว Control Navigation
    lateinit var appBarConfiguration: AppBarConfiguration // อ่านค่าจาก UI Navigation Component
    lateinit var navHostFragment: NavHostFragment // จัดการ host ของ fragment
    lateinit var mainDrawerLayout: DrawerLayout // จัดการ Drawer Menu
    lateinit var mainToolbar: Toolbar // จัดการ Toolbar
    lateinit var mainNavigationView: NavigationView // จัดการ Navigation View
    lateinit var mainBottomNavigationVeiw: BottomNavigationView // จัดการ Bottom Menu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ทำการ Find View
        mainToolbar = findViewById(R.id.main_toolbar)
        mainDrawerLayout = findViewById(R.id.drawer_layout)
        mainNavigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationVeiw = findViewById(R.id.main_bottom_navigation_view)

        // กำหนดค่าเริ่มต้นให้กับ navController
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // กำหนดการ appBarConfiguratio initial
        appBarConfiguration = AppBarConfiguration.Builder(
            // กำหนด Fragment ที่ไม่ต้องารกให้แสดงปุ่ม Back
            R.id.homeFragment,
            R.id.productFragment,
            R.id.addProductFragment,
            R.id.notificationFragment,
            R.id.accountFragment

        ).setOpenableLayout(mainDrawerLayout).build()

        // เรียกทำงานกับ Toolbar
        setSupportActionBar(mainToolbar)

        // กำหนด Toolbar ให้แสดง Icon Menu
        setupActionBarWithNavController(navController, appBarConfiguration)

        // เรียกใช้ Drawer navigation
        mainNavigationView.setupWithNavController(navController)

        // เรียกใช้งาน ฺBottom navigation
        mainBottomNavigationVeiw.setupWithNavController(navController)
    }

    // ทำการ Override method onSupportNavigateUp เพื่อจัดการเมนู
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}