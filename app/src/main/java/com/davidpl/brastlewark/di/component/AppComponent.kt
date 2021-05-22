package com.davidpl.brastlewark.di.component

import android.app.Application
import com.davidpl.brastlewark.MainApplication
import com.davidpl.brastlewark.di.module.AppModule
import com.davidpl.brastlewark.di.module.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(
    AppModule::class,
    FragmentBindingModule::class,
    AndroidSupportInjectionModule::class
))
interface AppComponent: AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}