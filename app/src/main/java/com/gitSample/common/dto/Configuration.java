package com.gitSample.common.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xyz on 8/14/2016.
 */
public class Configuration {

    public List<Navigation> navigation;
    @SerializedName("customapis")
    public CustomAPI customapi;

}
