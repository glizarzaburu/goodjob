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
    /* TODO 1.1 aqui declaras los views del layout.
        Ejemplo: private TextView fechaRegistro;
    * */

    TextView razonSocial;

    TextView nombreComercial;

    TextView ruc;

    TextView telefono;

    TextView correo;

    TextView codigoPostal;

    TextView distrito;

    TextView direccion;



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
        /* TODO 1.2 aqui haces los findViewById();
            Ejemplo: fechaRegistro = v.findViewById(R.id.tvFechaRegistro);
        * */

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
        /* TODO 1.3 y aca haces los setText para mostrar la info en el layout.
            Ejemplo: fechaRegistro.setText(e.getFechaRegistro());
        * */

        razonSocial.setText(e.getRazonSocial());
        nombreComercial.setText(e.getNombreComercial());
        ruc.setText(e.getRuc());
        telefono.setText(e.getTelefono());
        correo.setText(e.getCorreo());
        codigoPostal.setText(e.getCodigoPostal());
        distrito.setText(e.getDistrito());
        direccion.setText(e.getDireccion());

    }

    /* TODO 1.4 este solo es un recordatorio para que borres todos los TODO(1.0 - 1.4)
        una vez termines.
     * */

}
