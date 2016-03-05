package com.hoasen.studio.dailymailfeed.MainNews.View;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public interface IDailyMailView {

    void gotoDetailFrag(DailyMailAdapter.DailyMailViewHolder viewHolder);
    void loadData(VnreviewModel model);
    void showNotHaveInternetMsg();

}
