package com.davidpl.brastlewark.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.davidpl.brastlewark.R
import com.davidpl.brastlewark.business.model.User
import com.davidpl.brastlewark.databinding.DetailFragmentBinding
import com.davidpl.brastlewark.ui.utility.helper.Constants
import com.squareup.picasso.Picasso

class DetailFragment: Fragment() {

    lateinit var binding: DetailFragmentBinding
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        user = arguments?.getParcelable<User>(Constants.BUNDLE_USER) as User
        binding.user = user
        Picasso.get().load(user.thumbnail).error(R.drawable.ic_user).into(binding.photo)
        return binding.root
    }

}