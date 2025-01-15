package com.example.metropolitanmuseumapp.di

import android.content.Context
import com.example.metropolitanmuseumapp.R
import com.example.metropolitanmuseumapp.data.retrofit.MetMuseumAPI
import com.example.metropolitanmuseumapp.data.retrofit.MetMuseumRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): MetMuseumAPI {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        client.addInterceptor(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            chain.proceed(requestBuilder.build())
        })

        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofit.create(MetMuseumAPI::class.java)
    }

    @Provides
    @Singleton
    fun getMetMuseumRepo(api: MetMuseumAPI): MetMuseumRepo {
        return MetMuseumRepo(api)
    }
}