package com.hoasen.studio.dailymailfeed.MainNews.Presenter;

import com.hoasen.studio.dailymailfeed.Base.IPresenter;
import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public interface IDailyMailPresenter extends IPresenter<IDailyMailView> {
    void gotoDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder);
    void loadData();
}
