package com.davidpl.brastlewark.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidpl.brastlewark.R
import com.davidpl.brastlewark.business.model.User
import com.davidpl.brastlewark.databinding.ListFragmentBinding
import com.davidpl.brastlewark.ui.utility.helper.Constants
import com.davidpl.brastlewark.ui.viewmodel.ListViewModel
import com.example.display.ui.adapter.UsersAdapter
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
        configUsersObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        configUsersUI()
        binding.loadingContent.loading.visibility = View.VISIBLE
//        viewModel.getUsers()
        return binding.root
    }

    private fun configUsersObserver() {
        val observer = Observer<List<User>> { result ->
            userList.clear()
            userList.addAll(result)
            adapter.notifyDataSetChanged()
            binding.loadingContent.loading.visibility = View.GONE
        }
        viewModel.userListLiveData.observe(this, observer)
    }

    private fun configUsersUI() {
        userList = ArrayList()
        adapter = UsersAdapter(userList) { user ->
            val bundle = bundleOf(Constants.BUNDLE_USER to user)
            Navigation.findNavController(binding.root).navigate(R.id.action_list_to_detail, bundle)
        }
        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = llm
        binding.recyclerview.adapter = adapter
    }

}