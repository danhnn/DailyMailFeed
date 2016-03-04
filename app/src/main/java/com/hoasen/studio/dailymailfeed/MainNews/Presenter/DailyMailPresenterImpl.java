package com.hoasen.studio.dailymailfeed.MainNews.Presenter;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DailyMailPresenterImpl implements IDailyMailPresenter {
    IDailyMailView view;

    @Override
    public void gotoDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder) {
        view.gotoDetailFrag(viewHolder);
    }

    @Override
    public void loadData() {
        view.loadData();
    }

    @Override
    public void setView(IDailyMailView view) {
        this.view = view;
    }
}
