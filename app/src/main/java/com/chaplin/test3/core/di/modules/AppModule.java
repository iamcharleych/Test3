package com.chaplin.test3.core.di.modules;

import com.chaplin.test3.core.di.scopes.ActivityScope;
import com.chaplin.test3.ui.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {
        DBModule.class,
        ExecutionModule.class,
        NetworkModule.class,
        RepositoryModule.class
})
public abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivity();
}
