package com.example.apipaises;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipaises.Adapter.PaisAdapter;

import com.example.apipaises.dto.PaisDTO;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PaisAdapter paisAdapter;
    private List<PaisDTO> paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        paises = new ArrayList<>();
        paisAdapter = new PaisAdapter(this,paises);
        recyclerView.setAdapter(paisAdapter);

        new FetchPaisesTask().execute("https://falabr.cgu.gov.br/api/paises");
    }

    private class FetchPaisesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return fetchData(urls[0]);
            } catch (IOException e) {
                return "Erro ao buscar dados da API: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                parseJson(result);
            } catch (JSONException e) {
                Log.e("JSON", "Erro ao fazer parsing do JSON: " + e.getMessage());
            }

            // Atualize o RecyclerView com os dados obtidos
            paisAdapter.notifyDataSetChanged();
        }

        private String fetchData(String urlString) throws IOException {
            StringBuilder result = new StringBuilder();

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } finally {
                urlConnection.disconnect();
            }

            return result.toString();
        }

        private void parseJson(String json) throws JSONException {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int codigo = jsonObject.getInt("Código");
                String descricao = jsonObject.getString("Descrição");

                paises.add(new PaisDTO(codigo, descricao));
            }
        }
    }
}
