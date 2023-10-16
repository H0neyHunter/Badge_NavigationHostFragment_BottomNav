package com.usyssoft.badge_navigationhostfragment_bottom.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.usyssoft.badge_navigationhostfragment_bottom.R
import com.usyssoft.badge_navigationhostfragment_bottom.databinding.FragmentPlusBinding
import com.usyssoft.badge_navigationhostfragment_bottom.view.activity.MainActivity


class PlusFragment : Fragment() {
    private lateinit var b : FragmentPlusBinding


    private lateinit var context: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentPlusBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        b.apply {

            plusBadge.setOnClickListener {
                (context as MainActivity).badgeFunction(true,1,R.color.black,R.color.white,"BadgePlus")
            }
        }
        return b.root
    }
}