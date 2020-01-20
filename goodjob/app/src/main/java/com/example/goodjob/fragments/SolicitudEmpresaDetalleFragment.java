package com.example.goodjob.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodjob.R;
import com.example.goodjob.classes.Empresa;
import com.example.goodjob.classes.ValidSession;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class SolicitudEmpresaDetalleFragment extends Fragment {

    private Empresa empresa;
    private TextView razonSocial;
    private TextView nombreComercial;
    private TextView ruc;
    private TextView telefono;
    private TextView correo;
    private TextView codigoPostal;
    private TextView distrito;
    private TextView direccion;
    private Button aceptar;
    private TextView rechazar;

    public SolicitudEmpresaDetalleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_empresa_detalle, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null)
            empresa = bundle.getParcelable("empresa");
        mapearViews(v);
        mostrarDatos(empresa);

        aceptar();
        rechazar();

        return v;
    }

    private void aceptar() {
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String url = ValidSession.IP + "/ws_aceptar_empresa.php?id_empresa=" + empresa.getId();
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
                                            .replace(R.id.containerFragments, new ListaEmpresasEsperaFragment())
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
                String url = ValidSession.IP + "/ws_rechazar_empresa.php?id_empresa=" + empresa.getId();
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
                                            .replace(R.id.containerFragments, new ListaEmpresasEsperaFragment())
                                            .commit();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void mapearViews(View v) {
        razonSocial = v.findViewById(R.id.tvRazonSocial);
        nombreComercial = v.findViewById(R.id.tvNombreComercial);
        ruc = v.findViewById(R.id.tvRuc);
        telefono = v.findViewById(R.id.tvTelefono);
        correo = v.findViewById(R.id.tvCorreo);
        codigoPostal = v.findViewById(R.id.tvCodigoPostal);
        distrito = v.findViewById(R.id.tvDistrito);
        direccion = v.findViewById(R.id.tvDireccion);
        aceptar = v.findViewById(R.id.aceptar_empresa_button);
        rechazar = v.findViewById(R.id.rechazar_empresa_button);
    }

    private void mostrarDatos(Empresa e) {
        razonSocial.setText(e.getRazonSocial());
        nombreComercial.setText(e.getNombreComercial());
        ruc.setText(e.getRuc());
        telefono.setText(e.getTelefono());
        correo.setText(e.getCorreo());
        codigoPostal.setText(e.getCodigoPostal());
        distrito.setText(e.getDistrito());
        direccion.setText(e.getDireccion());

    }
}
