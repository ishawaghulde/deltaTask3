package com.example.android.yagami;

import com.google.gson.annotations.SerializedName;

public class Force {
    @SerializedName("id")
    private String forceId;

    private String name;

    public String getForceId() {
        return forceId;
    }

    public String getName() {
        return name;
    }
}
