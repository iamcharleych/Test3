package com.chaplin.test3.core.di.modules;

import com.chaplin.test3.data.network.client.RetrofitRestClient;
import com.chaplin.test3.data.network.client.RxErrorHandlingCallAdapterFactory;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chaplin.test3.App;
import com.chaplin.test3.BuildConfig;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Cache provideOkHttpCache(App application) {
        int cacheSize = 5 * 1024 * 1024; // 5 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    static RestClient<Flowable<DataResponse>> provideRestClient(Retrofit retrofit) {
        return new RetrofitRestClient(retrofit);
    }
}
