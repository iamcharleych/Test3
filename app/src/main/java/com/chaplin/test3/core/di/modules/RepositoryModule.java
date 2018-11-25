package com.chaplin.test3.core.di.modules;

import com.chaplin.test3.data.db.AppDatabase;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import com.chaplin.test3.data.pref.Pref;
import com.chaplin.test3.data.repository.SearchResultsRepositoryImpl;
import com.chaplin.test3.data.repository.SessionRepositoryImpl;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import com.chaplin.test3.domain.repository.SessionRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;

import javax.inject.Singleton;

@Module
public abstract class RepositoryModule {

    @Singleton
    @Provides
    static Session providesSession() {
        return new Session(Pref.Session.BASE_POLLING_URL.get());
    }

    @Singleton
    @Provides
    static SessionRepository providesSessionRepository(RestClient<Flowable<DataResponse>> client, Session session) {
        return new SessionRepositoryImpl(client, session);
    }

    @Singleton
    @Provides
    static SearchResultsRepository providesSearchResultsRepository(RestClient<Flowable<DataResponse>> client,
                                                            AppDatabase database, Session session) {
        return new SearchResultsRepositoryImpl(client, database, session);
    }
}
