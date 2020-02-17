package com.concept.shop.utils

import android.view.View

fun View.toTransitionGroup() = this to transitionName

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}