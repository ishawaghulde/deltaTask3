package com.example.android.yagami;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PoliceDetailApi {
    @GET("crimes-at-location")
    Call<List<DetailPost>> getDetailPosts(@QueryMap Map<String, String> parameters);
}
