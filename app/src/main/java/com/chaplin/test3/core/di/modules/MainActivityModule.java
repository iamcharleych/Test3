package com.chaplin.test3.core.di.modules;

import com.chaplin.test3.core.di.scopes.FragmentScope;
import com.chaplin.test3.ui.searchresults.SearchResultsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = SearchResultsModule.class)
    abstract SearchResultsFragment contributesSearchResultsFragment();
}
