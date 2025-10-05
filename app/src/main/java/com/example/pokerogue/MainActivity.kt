package com.example.pokerogue

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent

class MainActivity : ComponentActivity() {
    companion object { private const val TAG = "MainActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = "https://pokerogue.net/"

        try {
            // Spróbuj znaleźć dostawcę Custom Tabs (np. Chrome)
            val packageName = CustomTabsClient.getPackageName(this, null)

            if (packageName != null) {
                val builder = CustomTabsIntent.Builder()

                val customTabsIntent = builder.build()
                customTabsIntent.intent.setPackage(packageName) // Ustawiamy pakiet, aby wymusić użycie konkretnej przeglądarki
                customTabsIntent.launchUrl(this, Uri.parse(url))

            } else {
                // Fallback do domyślnej przeglądarki, jeśli nie ma dostawcy Custom Tabs
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "No activity to handle Custom Tab/ACTION_VIEW", e)
            // Dodatkowy fallback na wypadek błędu
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Custom Tab", e)
            // Dodatkowy fallback na wypadek błędu
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } finally {
            // Zamknij pustą aktywność, aby użytkownik nie mógł do niej wrócić
            finish()
        }
    }
}
