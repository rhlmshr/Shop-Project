package com.concept.shop.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.concept.shop.R
import com.concept.shop.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_item_play_review_sheet.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ItemPlayReviewSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var screenMode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_play_review_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

//        dialog?.window?.attributes?.windowAnimations = R.style.ReviewSheetDialogAnimation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        iv_action.setOnClickListener(this)
    }

    init {
        lifecycleScope.launchWhenStarted {
            animateScene1()
        }
    }

    override fun onClick(v: View?) {
        if (screenMode == 1) {
            grp_screen_1.toGone()

            grp_screen_2.toVisible()
            grp_wallet.toVisible()

            lifecycleScope.launch {
                animateScene2()
            }

        } else if (screenMode == 2) {
            grp_screen_2.toGone()
            iv_action.toInvisible()

            grp_screen_3.toVisible()

            lifecycleScope.launch {
                animateScene3()
            }
        }

        screenMode++
    }

    private suspend fun animateScene1() {
        delay(250)
        animSpringEntry(view_pic_1, view_text_1, view_subtext_1)
        animFadeUpEntry(iv_action)

        delay(100)
        animSpringEntry(view_pic_2, view_text_2, view_subtext_2)

        animScaleEntry(500, view_title)
    }

    private suspend fun animateScene2() {
        animScaleEntry(500, view_title_2)
        animScaleEntry(500, view_subtitle_2)

        delay(50)
        animSpringEntry(view_price_1)
        delay(50)
        animSpringEntry(tv_price_1)
        delay(50)
        animSpringEntry(view_price_2)
        delay(50)
        animSpringEntry(tv_price_2)
        animFadeUpEntry(tv_wallet, view_balance, view_small_wallet_2, view_wallet_2)
        delay(50)
        animSpringEntry(view_price_3)
        delay(50)
        animSpringEntry(tv_price_3)
    }

    private suspend fun animateScene3() {
        animScaleEntry(500, tv_ready)
        animScaleEntry(500, tv_name)

        delay(50)
        animSpringEntry(view_opt_1)
        delay(50)
        animSpringEntry(view_opt_2)
        animFadeUpEntry(iv_confirm)
        delay(50)
        animSpringEntry(view_opt_3)
        animFadeUpEntry(tv_confirm)
    }
}