package com.davidpl.brastlewark.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidpl.brastlewark.R
import com.davidpl.brastlewark.business.model.User
import com.davidpl.brastlewark.databinding.ListFragmentBinding
import com.davidpl.brastlewark.ui.utility.helper.Constants
import com.davidpl.brastlewark.ui.viewmodel.ListViewModel
import com.example.brastlewark.ui.list.manager.ListManager
import com.example.display.ui.adapter.UsersAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var userList: ArrayList<User>

    var searchBarHeight: Int = 0

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
        setHasOptionsMenu(true)
        configSearchBarUI()
        binding.loadingContent.loading.visibility = View.VISIBLE
        viewModel.getUsers()
        return binding.root
    }

    private fun configUsersObserver() {
        val observer = Observer<List<User>> { result ->
            userList.clear()
            userList.addAll(result)
            adapter.notifyDataSetChanged()
            adapter.updateTotalList()
            binding.loadingContent.loading.visibility = View.GONE
        }
        viewModel.userListLiveData.observe(this, observer)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search_id) {
            ListManager.expandSearchBar(binding, searchBarHeight)
        }
        return super.onOptionsItemSelected(item)
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

    private fun configSearchBarUI() {
        binding.searchbarContent.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                searchBarHeight = binding.searchbarContent.height
                ListManager.hideSearchBar(binding)
                binding.searchbarContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        binding.searchbarText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { adapter.filter(p0.toString()) }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.searchbarButton.setOnClickListener {
            ListManager.collapseSearchBar(binding, searchBarHeight)
            ListManager.hideSoftKeyboard(activity as Activity)
        }
    }

}