package com.argel.fakecelebrityvoices

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.argel.fakecelebrityvoices.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setUpView()
    }

    private fun setUpView() {
        val radius = 32f
        val bottomBarBackground = binding.mainBottomNav.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .build()

        val navigationView = binding.appbarLayout.baseToolbar[0] as ImageView
        navigationView.addCircleRipple()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}

private fun View.addCircleRipple() = with(TypedValue()) {
    isFocusable = true
    isClickable = true
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}