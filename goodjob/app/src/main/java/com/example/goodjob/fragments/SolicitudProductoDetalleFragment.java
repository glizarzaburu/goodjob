package com.example.goodjob.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.SolicitudProducto;
import com.example.goodjob.classes.ValidSession;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class SolicitudProductoDetalleFragment extends Fragment {

    private TextView producto, empresa, stock, valor, lugarCanje;
    private ImageView imagen;
    private SolicitudProducto sp;
    private Button aceptar, rechazar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitud_producto_detalle, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null)
            sp = bundle.getParcelable("producto");
        mapearViews(view);
        mostrarDatos(sp);
        aceptar();
        rechazar();
        return view;
    }

    private void mapearViews(View view) {
        producto = view.findViewById(R.id.tvNombreProducto);
        empresa = view.findViewById(R.id.tvEmpresaProducto);
        stock = view.findViewById(R.id.tvStockProducto);
        valor = view.findViewById(R.id.tvValorProducto);
        lugarCanje = view.findViewById(R.id.tvLugarCanjeProducto);
        imagen = view.findViewById(R.id.imgFotoProducto);
        aceptar = view.findViewById(R.id.btnAceptarProducto);
        rechazar = view.findViewById(R.id.btnRechazarProducto);
    }

    private void mostrarDatos(SolicitudProducto p) {
        producto.setText(p.getNombre());
        empresa.setText(p.getEmpresa());
        stock.setText(p.getStock().toString());
        valor.setText(p.getValor().toString());
        lugarCanje.setText(p.getLugarCanje());
        ImageRequest request = new ImageRequest(ValidSession.IMAGENES_PRODUCTOS + sp.getImagenUrl(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void aceptar() {
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String url = ValidSession.IP + "/ws_aceptar_producto.php?id_producto=" + sp.getId();
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String _response = response.body().string();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), _response, Toast.LENGTH_SHORT).show();
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.containerFragments, new SolicitudProductosFragment())
                                            .commit();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void rechazar() {
        rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String url = ValidSession.IP + "/ws_rechazar_producto.php?id_producto=" + sp.getId();
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String _response = response.body().string();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), _response, Toast.LENGTH_SHORT).show();
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.containerFragments, new SolicitudProductosFragment())
                                            .commit();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

}
