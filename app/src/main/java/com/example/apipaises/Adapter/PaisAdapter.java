package com.example.apipaises.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipaises.R;
import com.example.apipaises.dto.PaisDTO;

import java.util.List;

public class PaisAdapter extends RecyclerView.Adapter<PaisAdapter.ViewHolder> {

    private List<PaisDTO> paises;
    private Context context;

    public PaisAdapter(Context context, List<PaisDTO> paises) {
        this.context = context;
        this.paises = paises;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pais, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PaisDTO pais = paises.get(position);
        holder.textViewCodigo.setText(String.valueOf(pais.getCodigo()));
        holder.textViewDescricao.setText(pais.getDescricao());
    }

    @Override
    public int getItemCount() {
        return paises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodigo;
        TextView textViewDescricao;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewCodigo = itemView.findViewById(R.id.textViewCodigo);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
        }
    }
}
