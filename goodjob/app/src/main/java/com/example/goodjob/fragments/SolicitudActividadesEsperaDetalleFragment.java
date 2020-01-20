package com.example.goodjob.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodjob.R;
import com.example.goodjob.classes.Actividad;
import com.spark.submitbutton.SubmitButton;

public class SolicitudActividadesEsperaDetalleFragment extends Fragment {

    private Actividad actividad;
    /* TODO 2.1 aqui declaras los views del layout.
        Ejemplo: private TextView fechaRegistro;
    * */

    private TextView titulo;
    private TextView descripcion;
    private TextView empresa;
    private TextView fecha_creacion;
    private TextView fecha_fin;
    private TextView distrito;
    private TextView participantes_reque;
    private TextView tip_recompensa;
    private TextView trecompensa;
    SubmitButton submitButton;

    public SolicitudActividadesEsperaDetalleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_actividades_espera_detalle, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null)
            actividad = bundle.getParcelable("actividad");
        mapearViews(v);
        mostrarDatos(actividad);
        return v;


    }

    private void mapearViews(View v) {
        /* TODO 2.2 aqui haces los findViewById();
            Ejemplo: fechaRegistro = v.findViewById(R.id.tvFechaRegistro);
        * */

        titulo = v.findViewById(R.id.tvTitledetespera);
        descripcion = v.findViewById(R.id.tvDescriptiondetespera);
        empresa = v.findViewById(R.id.tvEmpresaNombre);
        fecha_creacion = v.findViewById(R.id.tvFechaCreaciondetalle);
        fecha_fin = v.findViewById(R.id.tvFechafinaldetalle);
        distrito = v.findViewById(R.id.tvDistritoEspeDet);
        participantes_reque = v.findViewById(R.id.tvParticipantesRequeridos);
        tip_recompensa = v.findViewById(R.id.tvRecompensaDet);
        trecompensa = v.findViewById(R.id.tvPuntosDet);


    }

    private void mostrarDatos(Actividad a) {
        /* TODO 2.3 y aca haces los setText para mostrar la info en el layout.
            Ejemplo: fechaRegistro.setText(a.getCreationDate());
        * */

        empresa.setText(a.getAuthor());
        descripcion.setText(a.getDescription());
        titulo.setText(a.getTitle());
        fecha_creacion.setText(a.getCreationDate());
        fecha_fin.setText(a.getEndDate());
        distrito.setText(a.getDistrito());
        tip_recompensa.setText(a.getRewardType());
        participantes_reque.setText(actividad.getRequiredParticipants() + " personas");
        trecompensa.setText(" "+actividad.getReward());


    }

    /* TODO 2.4 este solo es un recordatorio para que borres todos los TODO(2.0 - 2.4)
        una vez termines.
     * */

}
