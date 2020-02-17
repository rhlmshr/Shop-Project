package com.concept.shop.utils

import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun animSpringEntry(vararg view: View) {
    view.forEach {
        SpringAnimation(it, DynamicAnimation.TRANSLATION_X)
            .setSpring(
                SpringForce()
                    .setStiffness(SpringForce.STIFFNESS_LOW)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                    .setFinalPosition(0f)
            )
            .start()
    }
}

fun animFadeUpEntry(vararg view: View) {
    view.forEach {
        it.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(250)
            .setInterpolator(OvershootInterpolator())
    }
}

fun animScaleEntry(duration: Long, view: View) {
    view.animate()
        .scaleX(1f)
        .scaleY(1f)
        .setDuration(duration)
        .setInterpolator(OvershootInterpolator())
}