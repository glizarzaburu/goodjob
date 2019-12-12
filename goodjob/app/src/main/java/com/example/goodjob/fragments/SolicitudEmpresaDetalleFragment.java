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
    TextView razon;
    TextView razonSocial;

    TextView nom;
    TextView nombreComercial;

    TextView ru;
    TextView ruc;

    TextView telf;
    TextView telefono;

    TextView corr;
    TextView correo;

    TextView codigo;
    TextView codigoPostal;

    TextView distri;
    TextView distrito;

    TextView direcc;
    TextView direccion;

    ImageButton aceptarSolicitud;
    ImageButton rechazarSolicitud;

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
        razon = v.findViewById(R.id.tvRazon);
        razonSocial = v.findViewById(R.id.tvRazonSocial);

        nom = v.findViewById(R.id.tvNombre);
        nombreComercial = v.findViewById(R.id.tvNombreComercial);

        ru = v.findViewById(R.id.tvRu);
        ruc = v.findViewById(R.id.tvRuc);

        telf = v.findViewById(R.id.tvTelef);
        telefono = v.findViewById(R.id.tvTelefono);

        corr = v.findViewById(R.id.tvCorr);
        correo = v.findViewById(R.id.tvCorreo);

        codigo = v.findViewById(R.id.tvCodigo);
        codigoPostal = v.findViewById(R.id.tvCodigoPostal);

        distri = v.findViewById(R.id.tvDistri);
        distrito = v.findViewById(R.id.tvDistrito);

        direcc= v.findViewById(R.id.tvDirecc);
        direccion = v.findViewById(R.id.tvDireccion);

        aceptarSolicitud =v.findViewById(R.id.btnAceptarSolicitud);
        rechazarSolicitud = v.findViewById(R.id.btnRechazarSolicitud);


    }

    private void mostrarDatos(Empresa e) {
        /* TODO 1.3 y aca haces los setText para mostrar la info en el layout.
            Ejemplo: fechaRegistro.setText(e.getFechaRegistro());
        * */

        razonSocial.setText(e.getRazonSocial());
        telefono.setText(e.getRazonSocial());
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
