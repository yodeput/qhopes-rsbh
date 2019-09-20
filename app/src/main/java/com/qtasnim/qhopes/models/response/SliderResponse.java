package com.qtasnim.qhopes.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderResponse {


    @Expose
    @SerializedName("data")
    private List<Slider> data;
    @Expose
    @SerializedName("status")
    private boolean status;

    public List<Slider> getData() {
        return data;
    }

    public void setData(List<Slider> data) {
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
