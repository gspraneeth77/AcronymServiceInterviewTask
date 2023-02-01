package com.example.networking.di

import com.example.networking.data.AcromineService
import com.example.networking.data.RetrofitInstance
import com.example.networking.domain.Repo
import com.example.networking.repo.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Dependencies {

    @Provides
    @Singleton
    fun provideAcromineService(): AcromineService {
        return RetrofitInstance.acromineServiceInstance
    }

    @Provides
    @Singleton
    fun provideRepo(acromineApi: AcromineService): Repo {
        return RepoImpl(acromineApi)
    }

}