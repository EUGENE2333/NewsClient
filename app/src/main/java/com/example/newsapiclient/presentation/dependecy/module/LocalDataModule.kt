package com.example.newsapiclient.presentation.dependecy.module

import com.example.newsapiclient.data.db.ArticleDAO
import com.example.newsapiclient.data.repository.dataSource.NewsLocalDataSource
import com.example.newsapiclient.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}