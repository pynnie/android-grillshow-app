package de.shecken.grillshow.networking.youtube

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager.Period.ONE_HOUR
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import de.shecken.networking.BuildConfig
import de.shecken.networking.BuildConfig.YOUTUBE_DATA_API_BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
val networkModule = module {

    single {
        ChuckerCollector(
            context = get(),
            showNotification = true,
            retentionPeriod = ONE_HOUR
        )
    }

    single { ChuckerInterceptor.Builder(get()).build() }

    single { HttpLoggingInterceptor().apply { level = BODY } }

    single {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(get<ChuckerInterceptor>())
                        .addInterceptor(get<HttpLoggingInterceptor>())
                }
            }
            .build()
    }

    single { Json { ignoreUnknownKeys = true } }

    single {
        val contentType = "application/json".toMediaType()
        get<Json>().asConverterFactory(contentType)
    }

    single {
        Retrofit.Builder()
            .baseUrl(YOUTUBE_DATA_API_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(YoutubeDataApi::class.java)
    }
}
