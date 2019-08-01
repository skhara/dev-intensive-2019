package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("M_ProfileActivity", "onCreate")
    }
}
