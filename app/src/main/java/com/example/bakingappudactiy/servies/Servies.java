package com.example.bakingappudactiy.servies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Servies {


    //https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

    @GET("baking.json")
    Call<List<Baking>>readjosnObject();





}
