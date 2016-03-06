package com.hoasen.studio.dailymailfeed.MainNews.Presenter;

import android.content.Context;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.MainNewsActivity;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkService;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkServiceMock;
import com.hoasen.studio.dailymailfeed.Utilities.DMLog;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DailyMailPresenterImpl implements IDailyMailPresenter {
    IDailyMailView view;

    @Override
    public void gotoDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder) {
        view.gotoDetailFrag(viewHolder);
    }

    public boolean isHasInternet(){
        return Utilities.isNetworkConnected(MainNewsActivity.context);
    }

    @Override
    public void loadData() {

        if (isHasInternet() == false) {
            view.showNotHaveInternetMsg();
            return;
        }


        Observable<VnreviewModel> callNote = DMNetworkService.getInstance().getMobileReview();
        callNote.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnreviewModel -> {
                    view.loadData(vnreviewModel);
                });


    }

    @Override
    public void setView(IDailyMailView view) {
        this.view = view;
    }
}
