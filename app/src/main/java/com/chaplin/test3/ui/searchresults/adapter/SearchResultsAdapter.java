package com.chaplin.test3.ui.searchresults.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.chaplin.test3.core.model.mapper.SearchResultModelMapper;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.adapters.viewholders.BaseViewHolder;

public class SearchResultsAdapter extends
        PagedListAdapter<SearchResultEntity, SearchResultsAdapter.SearchResultsItemViewHolder> {

    private static final DiffUtil.ItemCallback<SearchResultEntity> DIFF = new DiffUtil.ItemCallback<SearchResultEntity>() {
        @Override
        public boolean areItemsTheSame(SearchResultEntity oldItem, SearchResultEntity newItem) {
            final String oldOutLegId = oldItem.getOutboundLegId();
            final String newOutLegId = newItem.getOutboundLegId();

            if (!oldOutLegId.equals(newOutLegId)) {
                return false;
            }

            final String oldInLegId = oldItem.getInboundLegId();
            final String newInLegId = newItem.getInboundLegId();

            return oldInLegId.equals(newInLegId);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SearchResultEntity oldItem, @NonNull SearchResultEntity newItem) {
            return false;
        }
    };

    @NonNull
    private final SearchResultsItemViewHolderCreator mViewHolderCreator;
    @NonNull
    private final SearchResultModelMapper mMapper;
    private LayoutInflater mLayoutInflater;

    public SearchResultsAdapter(@NonNull SearchResultsItemViewHolderCreator viewHolderCreator,
                                @NonNull SearchResultModelMapper mapper) {
        super(DIFF);
        mViewHolderCreator = viewHolderCreator;
        mMapper = mapper;
    }

    @NonNull
    @Override
    public SearchResultsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mViewHolderCreator.createViewHolder(getLayoutInflater(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsItemViewHolder holder, int position) {
        SearchResultsViewBinderItem item = getBinderItem(position);
        holder.bind(item);
    }

    @Override
    public void onCurrentListChanged(@Nullable PagedList<SearchResultEntity> currentList) {
        super.onCurrentListChanged(currentList);
    }

    private SearchResultsViewBinderItem getBinderItem(int position) {
        return new SearchResultsViewBinderItem(mMapper.map(getItem(position)));
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        return mLayoutInflater;
    }

    static class SearchResultsItemViewHolder extends
            BaseViewHolder<SearchResultsViewBinderItem, BaseViewBinder<SearchResultsViewBinderItem>> {

        SearchResultsItemViewHolder(@NonNull View itemView,
                                           @NonNull BaseViewBinder<SearchResultsViewBinderItem> binder) {
            super(itemView, binder);
        }
    }
}
