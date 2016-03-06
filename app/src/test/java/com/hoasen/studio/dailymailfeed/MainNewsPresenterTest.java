package com.hoasen.studio.dailymailfeed;

import android.content.Context;
import android.os.Environment;

import com.hoasen.studio.dailymailfeed.MainNews.DailyMailAdapter;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.DailyMailPresenterImpl;
import com.hoasen.studio.dailymailfeed.MainNews.Presenter.IDailyMailPresenter;
import com.hoasen.studio.dailymailfeed.MainNews.View.IDailyMailView;
import com.hoasen.studio.dailymailfeed.Networks.DMAPIService;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkService;
import com.hoasen.studio.dailymailfeed.Networks.DMNetworkServiceMock;
import com.hoasen.studio.dailymailfeed.Networks.FakeInterceptor;
import com.hoasen.studio.dailymailfeed.Utilities.DMLog;
import com.hoasen.studio.dailymailfeed.Utilities.Utilities;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public static Context context = RuntimeEnvironment.application;

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

        String responseString = getXml(RuntimeEnvironment.application,"xml_test.xml");
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.get();
        requestBuilder.url("http://localhost");

        Response response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(requestBuilder.build())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/xml"), responseString.getBytes()))
                .addHeader("content-type", "application/xml")
                .build();


        doReturn(Observable.just(response)).when(service).getMobileReview();

        TestSubscriber testSubscriber = new TestSubscriber();
        DMNetworkService.getInstance().getMobileReview().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

    }

    @Test
    public void checkRealDataLoadTest(){
        FakeInterceptor.responseString =  getXml(RuntimeEnvironment.application,"xml_test.xml");

        Observable<VnreviewModel> callNote = DMNetworkServiceMock.getInstance().getMobileReview();
        callNote.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnreviewModel -> {
                    assertThat(vnreviewModel.channel.listItem.get(0).title,is("Đánh giá nhanh Microsoft Lumia 550: có điểm gì đáng mua?"));
                });
    }

    private String getXml(Context context, String fileName) {
        String xmlString = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            xmlString = new String(data);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return xmlString;
    }
/*
    @Test
    public void goToDetailFragmentTest(){
        iDailyMailPresenter.gotoDetailFrag(viewHolder);
        verify(view).gotoDetailFrag(viewHolder);
    }
*/

}