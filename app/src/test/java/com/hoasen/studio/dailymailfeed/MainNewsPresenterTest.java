package com.hoasen.studio.dailymailfeed;

import com.hoasen.studio.dailymailfeed.Inject.AppClientComponent;
import com.hoasen.studio.dailymailfeed.Inject.DMNetworkClientModuleTest;
import com.hoasen.studio.dailymailfeed.Inject.DaggerDMNetworkClientComponentTest;
import com.hoasen.studio.dailymailfeed.Inject.MyApplication;
import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.DailyMailPresenterImpl;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.IDailyMailPresenter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.Networks.DMAPIService;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Harry Nguyen on 05-Mar-16.
 */
public class MainNewsPresenterTest extends ApplicationTestCase {

    @Mock
    IDailyMailView view;
    @Mock
    DailyMailAdapter.DailyMailViewHolder viewHolder;
    @Mock
    DMAPIService service;

    IDailyMailPresenter iDailyMailPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        iDailyMailPresenter = spy(new DailyMailPresenterImpl());
        iDailyMailPresenter.setView(view);

        // Inject our test component:
        AppClientComponent appClientComponent  =  DaggerDMNetworkClientComponentTest.builder().dMNetworkClientModuleTest(new DMNetworkClientModuleTest()).build();
        ((MyApplication) RuntimeEnvironment.application).setTestComponent(appClientComponent);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void loadDataTest(){
        doReturn(true).when(iDailyMailPresenter).isHasInternet();
        iDailyMailPresenter.loadData();

        ArgumentCaptor<VnreviewModel> argument = ArgumentCaptor.forClass(VnreviewModel.class);
        verify(view).showData(argument.capture());
        assertThat(argument.getValue().channel.listItem.get(0).title,is("Đánh giá nhanh Microsoft Lumia 550: có điểm gì đáng mua?"));
    }

    @Test
    public void goToDetailFragmentTest(){
        iDailyMailPresenter.gotoDetailFrag(viewHolder);
        verify(view).gotoDetailFrag(viewHolder);
    }


}
