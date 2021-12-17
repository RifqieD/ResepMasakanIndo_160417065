package com.example.resepmasakanindo_160417065.view.binding

import android.view.View
import androidx.databinding.BindingAdapter

object GoneUnless {
    @BindingAdapter("app:goneUnless")
    @JvmStatic
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}