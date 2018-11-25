package com.chaplin.test3.core.di.modules;

import javax.inject.Named;

import com.chaplin.test3.domain.execution.ExecutionThread;
import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class ExecutionModule {

    public static final String IO = "scheduler.io";
    public static final String ANDROID = "scheduler.android";

    @Provides
    @Named(IO)
    static ExecutionThread provideIOScheduler() {
        return Schedulers::io;
    }

    @Provides
    @Named(ANDROID)
    static ExecutionThread provideAndroidMainScheduler() {
        return AndroidSchedulers::mainThread;
    }
}
