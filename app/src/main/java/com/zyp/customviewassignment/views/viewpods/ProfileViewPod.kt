package com.zyp.customviewassignment.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.zyp.customviewassignment.databinding.ViewPodProfileBinding

class ProfileViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    private lateinit var binding: ViewPodProfileBinding
    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodProfileBinding.bind(this)
        setUpListener()
    }

    private fun setUpListener() {
        binding.imgProfile.setOnClickListener {
            mDelegate?.onTapProfile()
        }
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    interface Delegate {
        fun onTapProfile()
    }


}