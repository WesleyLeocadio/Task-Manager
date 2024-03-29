package com.example.taskmanager.views

import android.content.Intent
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.taskmanager.R
import com.example.taskmanager.ui.subject.HomeFragment
import com.example.taskmanager.ui.subject.RegisterSubjectFragment
import com.example.taskmanager.ui.task.TaskFragment
import com.example.taskmanager.util.SecurityPreferences
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_register_user.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var sharedPreferences: SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fm = supportFragmentManager
        fm.beginTransaction().replace(
            R.id.nav_host_fragment,
            HomeFragment(), "Fragment 1"
        ).commit()


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        sharedPreferences = SecurityPreferences(this)

        //menu lateral
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //menu lateral esquerdo onde fica localizado foto do usuário, nome e email
        val headView = navigationView.getHeaderView(0)
        val txtName = headView.findViewById<TextView>(R.id.textViewName)
        val txtEmail = headView.findViewById<TextView>(R.id.textViewEmail)
        txtEmail.text = sharedPreferences.getPreferences("USER_EMAIL")
        txtName.text = sharedPreferences.getPreferences("USER_NAME")

        //botão +
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //menu superior direito
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var fm = supportFragmentManager
        when (item.itemId) {
            R.id.nav_home -> fm.beginTransaction().replace(
                R.id.nav_host_fragment,
                HomeFragment(), "Fragment 1"
            ).commit()

            R.id.nav_register -> fm.beginTransaction().replace(
                R.id.nav_host_fragment,
                RegisterSubjectFragment(), "Fragment 1"
            ).commit()

            R.id.nav_done -> fm.beginTransaction().replace(
                R.id.nav_host_fragment,
                TaskFragment(1), "Fragment 1"
            ).commit()

            R.id.nav_todo -> fm.beginTransaction().replace(
                R.id.nav_host_fragment,
                TaskFragment(0), "Fragment 1"
            ).commit()

            R.id.nav_logout -> logout()


        }


        // Fecha o menu
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        sharedPreferences.setPreferences("USER_ID", "")
        sharedPreferences.setPreferences("USER_NAME", "")
        sharedPreferences.setPreferences("USER_TELEFHONE", "")
        sharedPreferences.setPreferences("USER_EMAIL", "")
        sharedPreferences.setPreferences("USER_PASSWORD", "")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
