package com.concept.shop.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.concept.shop.R
import com.concept.shop.utils.BaseFragment
import com.concept.shop.utils.toTransitionGroup
import kotlinx.android.synthetic.main.fragment_items_list.*

class ItemsListFragment : BaseFragment(), View.OnClickListener {

    override val layout: Int
        get() = R.layout.fragment_items_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        iv_background.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        findNavController().navigate(
            ItemsListFragmentDirections.actionItemsListFragmentToItemDetailsFragment2(),
            FragmentNavigatorExtras(
                iv_thumb.toTransitionGroup(),
                iv_download.toTransitionGroup()
            )
        )
    }
}
