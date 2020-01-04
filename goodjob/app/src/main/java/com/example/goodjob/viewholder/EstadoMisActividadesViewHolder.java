package com.example.goodjob.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.goodjob.R;
import com.example.goodjob.adapter.EstadoMisActividadesAdapter;

import java.text.BreakIterator;

public class EstadoMisActividadesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView titulo;
    public TextView fecha;
    public TextView estado;
    public TextView distrito;
    public View item;
    private EstadoMisActividadesAdapter.OnEstadoActividadListener onEstadoActividadListener;

    public EstadoMisActividadesViewHolder(@NonNull View itemView, EstadoMisActividadesAdapter.OnEstadoActividadListener onEstadoActividadListener) {
        super(itemView);

        titulo = itemView.findViewById(R.id.tvTitulo);
        fecha = itemView.findViewById(R.id.tvFecha);
        estado = itemView.findViewById(R.id.tvEstado);
        distrito = itemView.findViewById(R.id.tvDistritoValue);
        item = itemView.findViewById(R.id.textItem);

        this.onEstadoActividadListener = onEstadoActividadListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onEstadoActividadListener.onEstadoActividadClick(getAdapterPosition());
    }
}