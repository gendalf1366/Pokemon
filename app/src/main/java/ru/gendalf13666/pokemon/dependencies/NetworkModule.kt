package ru.gendalf13666.pokemon.dependencies

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gendalf13666.pokemon.PokemonApp
import ru.gendalf13666.pokemon.repository.network.PokemonService
import ru.gendalf13666.pokemon.util.INetworkStatus
import ru.gendalf13666.pokemon.util.NetworkStatusImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Named("baseUrl")
    fun baseUrlProvider(): String = PokemonService.BASE_URL

    @Provides
    fun loggerProvider(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Singleton
    @Provides
    fun okHttpProvider(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logger).build()

    @Singleton
    @Provides
    fun retrofitProvider(@Named("baseUrl") baseUrl: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun serviceProvider(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    fun networkStatusProvider(app: PokemonApp): INetworkStatus =
        NetworkStatusImpl(app)
}
