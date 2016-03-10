package com.hoasen.studio.dailymailfeed.MainNews.Presenter;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.MainNewsActivity;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkClient;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;

import rx.Observable;
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

        Observable<VnreviewModel> callNote = DMNetworkClient.getInstance().getMobileReview();
        callNote.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnreviewModel -> {
                    view.showData(vnreviewModel);
                });
    }

    @Override
    public void setView(IDailyMailView view) {
        this.view = view;
    }
}
