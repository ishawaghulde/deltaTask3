package com.example.android.yagami;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForcesAPI {
    @GET("forces")
    Call <List<Force>> getForces();
}
