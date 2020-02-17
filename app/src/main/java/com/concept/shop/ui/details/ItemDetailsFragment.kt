package com.concept.shop.ui.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.text.bold
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.concept.shop.R
import com.concept.shop.adapter.ThumbsAdapter
import com.concept.shop.utils.BaseFragment
import com.concept.shop.utils.toGone
import com.concept.shop.utils.toVisible
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SetTextI18n")
class ItemDetailsFragment : BaseFragment(), View.OnClickListener {

    override val layout: Int
        get() = R.layout.fragment_item_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setListeners()
    }

    init {
        // choreographing animation when screen appears
        lifecycleScope.launchWhenStarted {
            delay(200)
            tv_title.toVisible()
            delay(100)
            tv_desc.toVisible()
            delay(100)
            tv_read_more.toVisible()
        }
    }

    private fun setupViews() {
        rv_thumbs.adapter = ThumbsAdapter()

        tv_download.text = SpannableStringBuilder()
            .bold { append("DOWNLOAD\n") }
            .append("30 MB")

        iv_download_close.isActivated = true
    }

    private fun setListeners() {
        iv_download.setOnClickListener(this)
        iv_play.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == iv_download.id) {
            lifecycleScope.launch {

                // hacking to avoid multiple clicks
                // need to implement debounce with clicks for this
                if (iv_download_close.isVisible) return@launch

                // toggling views
                iv_download_close.toVisible()
                delay(100)
                iv_download_close_bg.toVisible()

                delay(300)

                // starting download
                tv_download.text = "20 MB / 30 MB"
                iv_download.setImageResource(R.drawable.main_bg_sec_green_curved)

                delay(200)

                for (i in 15..100) {

                    // mimicking realistic progress
                    if (i < 60)
                        delay(30)
                    else {
                        delay(10)

                        // animation cross to done change
                        if (i == 70) iv_download_close.isActivated = false
                    }

                    pb_download.progress = i
                }

                delay(200)

                // removing progress flow
                grp_download.toGone()
                iv_download_close_bg.toGone()
                iv_download_close.toGone()
                grp_download.requestLayout()

                delay(300)

                // showing play button
                iv_play.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            if (tv_play != null) tv_play.toVisible()

                            // blink animation for play button
                            if (iv_play != null) iv_play.startAnimation(
                                AnimationUtils.loadAnimation(
                                    context!!,
                                    R.anim.blink
                                )
                            )
                        }
                    })
            }
        } else if (v.id == iv_play.id) {
            findNavController().navigate(ItemDetailsFragmentDirections.actionItemDetailsFragment2ToItemPlayReviewSheetFragment2())
        }
    }
}