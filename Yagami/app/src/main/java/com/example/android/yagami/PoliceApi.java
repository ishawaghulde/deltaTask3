package com.example.android.yagami;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PoliceApi {
    @GET("crimes-at-location")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);
}
