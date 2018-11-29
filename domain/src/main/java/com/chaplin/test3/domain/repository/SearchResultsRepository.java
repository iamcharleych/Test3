package com.chaplin.test3.domain.repository;

import com.chaplin.test3.domain.model.SearchResult;
import io.reactivex.Flowable;

import java.util.List;

public interface SearchResultsRepository {
    Flowable<List<SearchResult>> search(int pageIndex, boolean appendResults);
}
