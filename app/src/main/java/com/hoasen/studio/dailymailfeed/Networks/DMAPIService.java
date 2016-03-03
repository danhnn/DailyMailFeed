package com.hoasen.studio.dailymailfeed.Networks;
import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public interface DMAPIService {

    @GET("13622")
    Call<VnreviewModel> getMobileReview();
}
