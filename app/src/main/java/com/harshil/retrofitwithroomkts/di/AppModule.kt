package com.harshil.retrofitwithroomkts.di

import android.content.Context
import androidx.room.Room
import com.harshil.retrofitwithroomkts.Dao.PostDao
import com.harshil.retrofitwithroomkts.Database.PostDatabase
import com.harshil.retrofitwithroomkts.Network.ApiService
import com.harshil.retrofitwithroomkts.Network.ApiServiceImpl
import com.harshil.retrofitwithroomkts.Repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): PostDatabase =
        Room.databaseBuilder(context, PostDatabase::class.java,"postDatabase")
            .build()

    @Provides
    fun providesPostDao(postDatabase: PostDatabase): PostDao =
        postDatabase.getPostDao()

    @Provides
    fun providesPostRepository(postDao: PostDao, apiServiceImpl: ApiServiceImpl): PostRepository =
        PostRepository(postDao,apiServiceImpl)

    @Provides
    fun providesBaseUrl():String="https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl:String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}