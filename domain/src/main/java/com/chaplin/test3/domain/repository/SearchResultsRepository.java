package com.chaplin.test3.domain.repository;

import io.reactivex.Flowable;

public interface SearchResultsRepository {
    Flowable<Void> search(int pageIndex, boolean appendResults);
}
