package com.hysea.animator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hysea.animator.propertyAnimator.PropertyAnimatorActivity
import com.hysea.animator.transformer.TransformerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toTransformer(view: View) {
        startActivity(Intent(this, TransformerActivity::class.java))
    }

    fun toPropertyAnimator(view: View) {
        startActivity(Intent(this, PropertyAnimatorActivity::class.java))
    }
}
