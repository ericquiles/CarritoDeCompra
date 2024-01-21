package com.example.quilessilgadom03.interfaces;

import com.example.quilessilgadom03.model.product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface crudinterface {
    @GET("product")
    Call<List<product>> getAll();
}
