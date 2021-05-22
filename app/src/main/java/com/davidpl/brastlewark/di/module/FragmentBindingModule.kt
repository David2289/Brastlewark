package com.davidpl.brastlewark.di.module

import com.davidpl.brastlewark.ui.fragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun bindListFragment(): ListFragment

}