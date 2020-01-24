package com.example.goodjob.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goodjob.R;
import com.example.goodjob.classes.SolicitudProducto;

import java.util.List;

public class SolicitudProductoAdapter extends RecyclerView.Adapter<SolicitudProductoAdapter.SolicitudProductoViewHolder> {

    private List<SolicitudProducto> productos;

    public SolicitudProductoAdapter(List<SolicitudProducto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public SolicitudProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_solicitud_productos, viewGroup, false);
        return new SolicitudProductoViewHolder(view);
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

    class SolicitudProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre, valor, empresa;

        private SolicitudProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tv_producto_nombre_value);
            valor = itemView.findViewById(R.id.tv_producto_valor_value);
            empresa = itemView.findViewById(R.id.tv_producto_empresa_value);
        }
    }
}
