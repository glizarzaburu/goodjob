package com.example.goodjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodjob.R;
import com.example.goodjob.classes.Empresa;

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
