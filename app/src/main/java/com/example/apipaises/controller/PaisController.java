package com.example.apipaises.controller;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apipaises.Adapter.PaisAdapter;
import com.example.apipaises.dto.PaisDTO;
import com.example.apipaises.retrofit.RetrofitConfig;
import com.example.apipaises.service.IPaisService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisController {

    public static void getPaises(Context context, RecyclerView recyclerView) {
        IPaisService service = RetrofitConfig.getPaisService();
        Call<List<PaisDTO>> call = service.getPaises();

        call.enqueue(new Callback<List<PaisDTO>>() {
            @Override
            public void onResponse(Call<List<PaisDTO>> call, Response<List<PaisDTO>> response) {
                List<PaisDTO> paises = response.body();
                if (paises != null) {
                    setupRecyclerView(context, recyclerView, paises);
                }
            }

            @Override
            public void onFailure(Call<List<PaisDTO>> call, Throwable t) {
                // Tratar falha na requisição
            }
        });
    }

    private static void setupRecyclerView(Context context, RecyclerView recyclerView, List<PaisDTO> paises) {
        PaisAdapter paisAdapter = new PaisAdapter(context, paises);
        recyclerView.setAdapter(paisAdapter);
    }
}
