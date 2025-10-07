package com.example.pokerogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.androidbrowserhelper.trusted.TwaLauncher
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val twaLauncher = TwaLauncher(this)
        twaLauncher.launch("https://pokerogue.net/".toUri())

        finish()
    }
}