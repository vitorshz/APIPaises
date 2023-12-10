package com.example.apipaises.service;

import com.example.apipaises.dto.PaisDTO;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface IPaisService {
    @GET("ws/{codigo}/{descricao}/json/")
    Call<PaisDTO> getViaPais(@Path("codigo") int codigo, @Path("descricao") String descricao);

    @GET("paises")
    Call<List<PaisDTO>> getPaises();
}
