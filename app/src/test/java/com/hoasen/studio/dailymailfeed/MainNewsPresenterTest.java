package com.hoasen.studio.dailymailfeed;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.DailyMailPresenterImpl;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.IDailyMailPresenter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.Networks.DMAPIService;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkService;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkServiceMock;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;

import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Harry Nguyen on 05-Mar-16.
 */
public class MainNewsPresenterTest extends ApplicationTestCase implements DMAPIService {

    @Mock
    IDailyMailView view;
    @Mock
    DailyMailAdapter.DailyMailViewHolder viewHolder;

    IDailyMailPresenter iDailyMailPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        iDailyMailPresenter = spy(new DailyMailPresenterImpl());
        iDailyMailPresenter.setView(view);
    }

    @Test
    public void loadData(){
        doReturn(true).when(iDailyMailPresenter).isHasInternet();
        iDailyMailPresenter.loadData();

        Observable<VnreviewModel> callNote = DMNetworkServiceMock.getInstance().getMobileReview();
        callNote.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VnreviewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VnreviewModel vnreviewModel) {

                    }
                });

    }

    @Test
    public void goToDetailFragmentTest(){
        iDailyMailPresenter.gotoDetailFrag(viewHolder);
        verify(view).gotoDetailFrag(viewHolder);
    }

    @Override
    public Observable<VnreviewModel> getMobileReview() {
        return null;
    }
}
