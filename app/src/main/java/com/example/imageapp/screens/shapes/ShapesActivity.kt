package com.example.imageapp.screens.shapes

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.imageapp.R
import com.example.imageapp.databinding.ShapesActivityBinding


class ShapesActivity : AppCompatActivity() {
    lateinit var binding: ShapesActivityBinding
    var viewId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.shapes_activity)
        addInitialViews()
    }

    private fun addInitialViews() {
        binding.btnCircle.background = getCircle(Color.BLUE)

        binding.btnSquare.background = getSquare(Color.RED)

        binding.btnSquare.setOnClickListener {
            val view = getView()
            view.background = getSquare(Color.YELLOW)
            binding.parentLayout.addView(view)
        }

        binding.btnCircle.setOnClickListener {
            val view = getView()
            view.background = getCircle(Color.GREEN)
            binding.parentLayout.addView(view)
        }

        binding.btnUndo.setOnClickListener {
           if (viewId!=0){
               binding.parentLayout.removeView(findViewById(viewId))
               viewId = viewId.dec()
           }
        }
    }

    private fun getCircle(borderColor: Int): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(borderColor)
        shape.setStroke(3, borderColor)
        return shape
    }

    private fun getSquare(borderColor: Int): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(borderColor)
        shape.setStroke(3, borderColor)
        return shape
    }

    private fun getView(): View = View(this).apply {
        id = viewId.inc()
        viewId = id
        layoutParams = ViewGroup.LayoutParams(100, 100)
    }

}