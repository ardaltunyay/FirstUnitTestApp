package net.altunyay.myunittestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import net.altunyay.myunittestapp.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openLogin(view: View) {
        Intent(this@MainActivity, LoginActivity::class.java).apply {
            startActivity(this)
        }
    }
}