package com.example.goodjob.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.ProductoCanje;
import com.example.goodjob.classes.ValidSession;

import java.util.List;

public class ProductoCanjeAdapter extends RecyclerView.Adapter<ProductoCanjeAdapter.ProductoCanjeViewHolder> {

    private List<ProductoCanje> productosCanjeables;

    public ProductoCanjeAdapter(List<ProductoCanje> productosCanjeables) {
        this.productosCanjeables = productosCanjeables;
    }

    @NonNull
    @Override
    public ProductoCanjeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_productos_canje, viewGroup, false);
        return new ProductoCanjeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductoCanjeViewHolder pvh, int i) {
        ProductoCanje p = productosCanjeables.get(i);
        pvh.producto.setText(p.getProducto());
        pvh.stock.setText(String.valueOf(p.getStock()));
        pvh.valor.setText(String.valueOf(p.getValor()));
        ImageRequest imageRequest = new ImageRequest(ValidSession.IMAGENES_PRODUCTOS + p.getImagen(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                pvh.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        pvh.lugarCanje.setText(p.getLugarCanje());
        Volley.newRequestQueue(pvh.itemView.getContext()).add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return productosCanjeables.size();
    }

    static class ProductoCanjeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView producto, stock, valor, lugarCanje;

        private ProductoCanjeViewHolder(@NonNull View v) {
            super(v);
            imagen = v.findViewById(R.id.ivProductoCanjeImagen);
            producto = v.findViewById(R.id.tvProductoCanjeValue);
            stock = v.findViewById(R.id.tvProductoCanjeStockValue);
            valor = v.findViewById(R.id.tvProductoCanjeValorValue);
            // TODO: 1.1 mapeas el lugarCanje con el id que le hayas colocado en el item_productos_canje.xml
        }
    }
}
