package com.example.goodjob;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.classes.PerfilUsuario;
import com.example.goodjob.classes.ValidSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private TextView nombreCompleto;
    private TextView reputacion;
    private TextView puntaje;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mapearCampos(view);

        Bundle bundle = this.getArguments();
        Integer idUsuario = null;

        if (bundle != null)
            idUsuario = bundle.getInt("id");

        if (idUsuario == null && ValidSession.usuarioLogueado != null)
            idUsuario = ValidSession.usuarioLogueado.getId();

        if (ValidSession.usuarioLogueado == null)
            return view;

        consultarPerfilUsuario(idUsuario);

        return view;
    }

    private void mapearCampos(View view)
    {
        nombreCompleto = view.findViewById(R.id.tvNombreCompletoPerfil);
        reputacion = view.findViewById(R.id.tvReputacionPerfil);
        puntaje = view.findViewById(R.id.tvPuntajePerfil);
    }

    private void consultarPerfilUsuario(Integer idUsuario)
    {
        String url = ValidSession.IP + "/ws_consultarPerfilUsuario.php?id_usuario=" + idUsuario;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    PerfilUsuario perfilUsuario = PerfilUsuario.cargarDataDesdeJsonObject(jsonObject);
                    setearCamposEnPantalla(perfilUsuario);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void setearCamposEnPantalla(PerfilUsuario perfilUsuario)
    {
        nombreCompleto.setText(perfilUsuario.getNombre());
        reputacion.setText(String.valueOf(perfilUsuario.getReputacion()));
        puntaje.setText(String.valueOf(perfilUsuario.getPuntaje()));
    }

}
