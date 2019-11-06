package com.example.weathermap.ui.model;

import com.google.gson.annotations.SerializedName;

public class weather
{
    @SerializedName("tmp")
    public int tmp;
    @SerializedName("humidity")
    public String humidity;
    @SerializedName("dt")
    public float dt;
    @SerializedName("id")
    public String id;
}
