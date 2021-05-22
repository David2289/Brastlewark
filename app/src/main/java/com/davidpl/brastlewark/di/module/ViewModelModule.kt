package com.davidpl.brastlewark.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidpl.brastlewark.di.key.ViewModelKey
import com.davidpl.brastlewark.ui.viewmodel.ListViewModel
import com.example.brastlewark.ui.utility.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = arrayOf(RepositoryModule::class))
abstract class ViewModelModule {

    @Binds
    abstract fun binViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

}