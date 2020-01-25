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
import com.example.goodjob.classes.Actividad;
import com.example.goodjob.classes.ValidSession;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class SolicitudActividadesEsperaDetalleFragment extends Fragment {

    private Actividad actividad;
    private TextView titulo;
    private TextView descripcion;
    private TextView empresa;
    private TextView fecha_creacion;
    private TextView fecha_fin;
    private TextView distrito;
    private TextView participantes_reque;
    private TextView tip_recompensa;
    private TextView trecompensa;
    private ImageView imagen;
    private Button aceptar, rechazar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_actividades_espera_detalle, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null)
            actividad = bundle.getParcelable("actividad");

        mapearViews(v);
        mostrarDatos(actividad);
        aceptar();
        rechazar();

        return v;
    }

    private void mapearViews(View v) {
        titulo = v.findViewById(R.id.tvTitledetespera);
        descripcion = v.findViewById(R.id.tvDescriptiondetespera);
        empresa = v.findViewById(R.id.tvEmpresaNombre);
        fecha_creacion = v.findViewById(R.id.tvFechaCreaciondetalle);
        fecha_fin = v.findViewById(R.id.tvFechafinaldetalle);
        distrito = v.findViewById(R.id.tvDistritoEspeDet);
        participantes_reque = v.findViewById(R.id.tvParticipantesRequeridos);
        tip_recompensa = v.findViewById(R.id.tvRecompensaDet);
        trecompensa = v.findViewById(R.id.tvPuntosDet);
        imagen = v.findViewById(R.id.imgEsperaDet);
        aceptar = v.findViewById(R.id.btnAceptarActividad);
        rechazar = v.findViewById(R.id.btnRechazrActividad);
    }

    private void mostrarDatos(Actividad a) {
        empresa.setText(a.getAuthor());
        descripcion.setText(a.getDescription());
        titulo.setText(a.getTitle());
        fecha_creacion.setText(a.getCreationDate());
        fecha_fin.setText(a.getEndDate());
        distrito.setText(a.getDistrito());
        tip_recompensa.setText(a.getRewardType());
        participantes_reque.setText(actividad.getRequiredParticipants() + " personas");
        trecompensa.setText(" " + actividad.getReward());

        ImageRequest request = new ImageRequest(ValidSession.IMAGENES_ACTIVIDADES + actividad.getPhoto(), new Response.Listener<Bitmap>() {
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
                String url = ValidSession.IP + "/ws_aceptar_actividad.php?id_actividad=" + actividad.getId();
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
                                            .replace(R.id.containerFragments, new SolicitudActividadesEsperaFragment())
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
                String url = ValidSession.IP + "/ws_rechazar_actividad.php?id_actividad=" + actividad.getId();
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
                                            .replace(R.id.containerFragments, new SolicitudActividadesEsperaFragment())
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
