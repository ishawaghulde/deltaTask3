package com.example.android.yagami;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailForcesAPI {
    @GET("forces/{place_name}")
    Call<DetailForce> getDetailForces(@Path("place_name") String placeName);
}
