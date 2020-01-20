package com.example.goodjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.Empresa;
import com.example.goodjob.classes.ValidSession;

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

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String url = ValidSession.IP + "/ws_aceptar_empresa.php?id_empresa=" + empresa.getId();
                StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(view.getContext(), response, Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.containerFragments, new ListaEmpresasEsperaFragment())
                                .commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
                request.setShouldRetryServerErrors(true);
                Volley.newRequestQueue(view.getContext()).add(request);
            }
        });

        rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String url = ValidSession.IP + "/ws_rechazar_empresa.php?id_empresa=" + empresa.getId();
                StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(view.getContext(), response, Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.containerFragments, new ListaEmpresasEsperaFragment())
                                .commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
                request.setShouldRetryServerErrors(true);
                Volley.newRequestQueue(view.getContext()).add(request);
            }
        });

        return v;
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
