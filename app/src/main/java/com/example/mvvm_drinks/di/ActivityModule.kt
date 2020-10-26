package com.example.mvvm_drinks.di

import com.example.mvvm_drinks.data.model.DataSourceImpl
import com.example.mvvm_drinks.domain.DataSource
import com.example.mvvm_drinks.domain.Repository
import com.example.mvvm_drinks.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepositoryImpl(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindDataSourceImpl(dataSourceImpl: DataSourceImpl): DataSource


}