package com.example.brastlewark.ui.list.manager

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintSet
import com.davidpl.brastlewark.databinding.ListFragmentBinding

class ListManager {

    companion object {
        val ANIM_DURATION: Long = 250
        var SEARCHBAR_SHOWN = false

        fun hideSearchBar(binding: ListFragmentBinding) {
            val set = ConstraintSet()
            set.clone(binding.mainContent)
            set.constrainHeight(binding.searchbarContent.id, 0)
            set.applyTo(binding.mainContent)
            SEARCHBAR_SHOWN = false
        }

        fun collapseSearchBar(binding: ListFragmentBinding, searchBarHeight: Int) {
            if (!SEARCHBAR_SHOWN) return

            val set = ConstraintSet()
            set.clone(binding.mainContent)

            val animator: ValueAnimator = ValueAnimator.ofInt(searchBarHeight, 0)
            animator.duration = ANIM_DURATION
            animator.addUpdateListener { animation ->
                set.constrainHeight(binding.searchbarContent.id, animation.animatedValue as Int)
                set.applyTo(binding.mainContent)
            }
            animator.start()
            SEARCHBAR_SHOWN = false
        }

        fun expandSearchBar(binding: ListFragmentBinding, searchBarHeight: Int) {
            if (SEARCHBAR_SHOWN) return

            val set = ConstraintSet()
            set.clone(binding.mainContent)

            val animator: ValueAnimator = ValueAnimator.ofInt(0, searchBarHeight)
            animator.duration = ANIM_DURATION
            animator.addUpdateListener { animation ->
                set.constrainHeight(binding.searchbarContent.id, animation.animatedValue as Int)
                set.applyTo(binding.mainContent)
            }
            animator.start()
            SEARCHBAR_SHOWN = true
        }

        fun hideSoftKeyboard(activity: Activity) {
            val view = activity.currentFocus
            if (view != null) {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
    }

}