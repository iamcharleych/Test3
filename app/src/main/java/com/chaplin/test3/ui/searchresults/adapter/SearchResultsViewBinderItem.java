package com.chaplin.test3.ui.searchresults.adapter;

import androidx.annotation.NonNull;
import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinderItem;

public class SearchResultsViewBinderItem extends BaseViewBinderItem<SearchResultModel> {

    @NonNull
    private final SearchResultModel mEntity;

    public SearchResultsViewBinderItem(SearchResultModel entity) {
        mEntity = entity;
    }


    @Override
    public SearchResultModel getEntity() {
        return mEntity;
    }
}
