package com.davidpl.brastlewark.di.module

import com.davidpl.brastlewark.business.repository.UsersRepository
import com.example.display.business.datasource.local.UsersLocalDataSource
import com.example.display.business.datasource.remote.UsersRemoteDataSource
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(DataSourceModule::class))
class RepositoryModule {

    @Provides
    fun provideUsersRepository(remoteDataSource: UsersRemoteDataSource): UsersRepository {
        return UsersRepository(remoteDataSource)
    }

}