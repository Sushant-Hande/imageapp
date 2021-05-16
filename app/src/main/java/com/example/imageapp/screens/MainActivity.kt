package com.example.imageapp.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.imageapp.R
import com.example.imageapp.databinding.MainActivityBinding
import com.example.imageapp.screens.images.ImagesActivity
import com.example.imageapp.screens.shapes.ShapesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.btnShapes.setOnClickListener {
            startActivity(Intent(this, ShapesActivity::class.java))
        }

        binding.btnImages.setOnClickListener {
            startActivity(Intent(this, ImagesActivity::class.java))
        }
    }
}