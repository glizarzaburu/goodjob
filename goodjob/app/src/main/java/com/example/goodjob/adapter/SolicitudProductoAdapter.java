package com.example.goodjob.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goodjob.R;
import com.example.goodjob.classes.SolicitudProducto;
import com.example.goodjob.interfaces.OnSolicitudProductoListener;

import java.util.List;

public class SolicitudProductoAdapter extends RecyclerView.Adapter<SolicitudProductoAdapter.SolicitudProductoViewHolder> {

    private List<SolicitudProducto> productos;
    private OnSolicitudProductoListener listener;

    public SolicitudProductoAdapter(List<SolicitudProducto> productos, OnSolicitudProductoListener listener) {
        this.productos = productos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SolicitudProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_solicitud_productos, viewGroup, false);
        return new SolicitudProductoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudProductoViewHolder spv, int i) {
        SolicitudProducto sp = productos.get(i);
        spv.empresa.setText(sp.getEmpresa());
        spv.nombre.setText(sp.getNombre());
        spv.valor.setText(sp.getValor().toString());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class SolicitudProductoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombre, valor, empresa;
        private OnSolicitudProductoListener listener;

        private SolicitudProductoViewHolder(@NonNull View itemView, final OnSolicitudProductoListener listener) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tv_producto_nombre_value);
            valor = itemView.findViewById(R.id.tv_producto_valor_value);
            empresa = itemView.findViewById(R.id.tv_producto_empresa_value);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onSolicitudProductoClicl(getAdapterPosition());
        }
    }
}
