package com.hoasen.studio.dailymailfeed.MainNews;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoasen.studio.dailymailfeed.Base.BaseFragment;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.DailyMailPresenterImpl;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.IDailyMailPresenter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkService;
import com.hoasen.studio.dailymailfeed.NewsDetail.NewDetailFragment;
import com.hoasen.studio.dailymailfeed.R;
import com.hoasen.studio.dailymailfeed.Utilities.ConstantValue;
import com.hoasen.studio.dailymailfeed.Utilities.DMFragmentManager;
import com.hoasen.studio.dailymailfeed.Utilities.DetailsTransition;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;


import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainNewsFragment extends BaseFragment implements DailyMailAdapter.IDailyMailViewHolderClicks, IDailyMailView {

    @Bind(R.id.cardList)
    RecyclerView cardList;
    DailyMailAdapter adapter;
    IDailyMailPresenter iDailyMailPresenter;
    @Bind(R.id.progressbar_view)
    LinearLayout progressbarView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = mainView == null ? inflater.inflate(R.layout.fragment_main_news, container, false) : mainView;
        ButterKnife.bind(this, mainView);

        return mainView;
    }

    @Override
    public void setupView() {
        iDailyMailPresenter = new DailyMailPresenterImpl();
        iDailyMailPresenter.setView(this);

        setupRecycleList();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            iDailyMailPresenter.loadData();
        });
    }

    void setupRecycleList() {
        cardList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        cardList.setLayoutManager(llm);

        adapter = adapter == null ? new DailyMailAdapter(getContext()) : adapter;
        adapter.setListener(MainNewsFragment.this);
        cardList.setAdapter(adapter);
    }

    @Override
    protected void handleLogicForOnceTime() {
        super.handleLogicForOnceTime();
        iDailyMailPresenter.loadData();
    }


    @Override
    public void loadData(VnreviewModel model) {
        adapter.setData(model.channel.listItem);
        adapter.notifyDataSetChanged();
        onItemsLoadComplete();
    }

    @Override
    public void showNotHaveInternetMsg(){
        Snackbar.make(mainView, "Please check your internet connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    void onItemsLoadComplete() {
        // Stop refresh animation
        progressbarView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDailyMailClick(DailyMailAdapter.DailyMailViewHolder viewHolder) {
        iDailyMailPresenter.gotoDetailFrag(viewHolder);
    }

    @Override
    public void gotoDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder) {
        NewDetailFragment desFrag = setUpDetailFrag(viewHolder);

        DMFragmentManager.getInstance().transactionWithAnimationTo(desFrag, viewHolder.ivNews, getString(R.string.transition_detail_name));
    }

    NewDetailFragment setUpDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder) {
        NewDetailFragment desFrag = (NewDetailFragment) DMFragmentManager.getInstance().getFragment(ConstantValue.FRAGMENT_DETAIL);

        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.URL_KEY, viewHolder.url);
        bundle.putString(ConstantValue.TITLE_KEY, viewHolder.tvTitle.getText().toString());
        bundle.putString(ConstantValue.BODY_KEY, viewHolder.tvDesc.getText().toString());
        Bitmap bm = ((BitmapDrawable) viewHolder.ivNews.getDrawable()).getBitmap();
        bundle.putParcelable(ConstantValue.BITMAP_KEY, bm);
        desFrag.setData(bundle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            desFrag.setSharedElementEnterTransition(new DetailsTransition());
            desFrag.setEnterTransition(new Fade());
            desFrag.setSharedElementReturnTransition(new DetailsTransition());
            setExitTransition(new Fade());
        }

        return desFrag;
    }
}
