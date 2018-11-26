package com.chaplin.test3.ui.searchresults;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.chaplin.test3.R;
import com.chaplin.test3.core.model.mapper.SearchResultModelMapper;
import com.chaplin.test3.core.utils.DataFormatUtils;
import com.chaplin.test3.data.Constants;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsAdapter;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsItemViewHolderCreator;
import com.chaplin.test3.ui.searchresults.viewmodel.SearchResultsViewModel;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

public class SearchResultsFragment extends Fragment implements HasSupportFragmentInjector {

    private SearchResultsAdapter mAdapter;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;
    @Inject
    SearchResultsViewModel.Factory mViewModelFactory;
    @Inject
    SearchResultModelMapper mMapper;

    @Nullable
    private SearchResultsView mSearchResultsView;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupViewModel(view);
        setupActionBar();
    }

    private void setupViewModel(@NonNull View rootView) {
        SearchResultsViewModel viewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(SearchResultsViewModel.class);

        SearchResultsViewModel.Data data = viewModel.getData();

        mSearchResultsView = new SearchResultsView(rootView,
                getAdapter(),
                getViewLifecycleOwner(),
                data.getDataLoadingViewState(),
                data.getPagedList());

        mSearchResultsView.registerActionsObserver(viewModel.getActionConsumer());
    }

    private void setupActionBar() {
        Activity activity = getActivity();
        if (!(activity instanceof AppCompatActivity)) {
            return; // early exit
        }

        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (actionBar == null) {
            return; // early exit
        }

        actionBar.setTitle(DataFormatUtils.formatSearchScreenTitle(Constants.SEARCH_ORIGIN, Constants.SEARCH_DESTINATION));
        actionBar.setSubtitle(DataFormatUtils.formatSearchScreenSubtitle(Constants.OUTBOUND_DATE, Constants.RETURN_DATE,
                Constants.ADULTS_COUNT, Constants.CHILDREN_COUNT, Constants.INFANTS_COUNT, Constants.CABIN_CLASS));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_results, menu);
    }

    private SearchResultsAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new SearchResultsAdapter(new SearchResultsItemViewHolderCreator(R.layout.view_results_list_item),
                    mMapper);
        }

        return mAdapter;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }
}
