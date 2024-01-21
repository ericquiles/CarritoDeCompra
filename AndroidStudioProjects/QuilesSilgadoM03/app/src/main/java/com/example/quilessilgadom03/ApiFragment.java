package com.example.quilessilgadom03;

import retrofit2.Response;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quilessilgadom03.interfaces.crudinterface;
import com.example.quilessilgadom03.model.product;
import com.example.quilessilgadom03.utils.constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFragment extends Fragment {

    private List<product> products;
    private crudinterface crudInterface;

    public ApiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api, container, false);
        // Add your UI-related code here if needed
        fetchData(); // Call the method to trigger the network request
        return view;
    }

    // Add this method to trigger the network request
    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(crudinterface.class);
        Call<List<product>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<product>>() {

            @Override
            public void onResponse(Call<List<product>> call, Response<List<product>> response) {
                if (!response.isSuccessful()) {
                    Log.e("error ", response.message());
                    return;
                }
                products = response.body();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    products.forEach(p -> Log.i("api  ", p.toString()));
                }
            }

            @Override
            public void onFailure(Call<List<product>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}

