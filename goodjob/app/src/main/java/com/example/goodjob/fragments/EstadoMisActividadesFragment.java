package com.example.goodjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.goodjob.R;
import com.example.goodjob.adapter.EstadoMisActividadesAdapter;
import com.example.goodjob.classes.EstadoMisActividadesResponse;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.util.Certificado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EstadoMisActividadesFragment extends Fragment implements EstadoMisActividadesAdapter.OnEstadoActividadListener {

    private RecyclerView rvEstadoMisActividades;
    private List<EstadoMisActividadesResponse> misActividades = new ArrayList<>();


    public EstadoMisActividadesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_estado_mis_actividades, container, false);

        rvEstadoMisActividades = view.findViewById(R.id.rvMisActividades);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rvEstadoMisActividades.setLayoutManager(lm);
        rvEstadoMisActividades.setHasFixedSize(true);

        if (ValidSession.usuarioLogueado != null)
            cargarData(ValidSession.usuarioLogueado.getId());

        Certificado.handleSSLHandshake();
        return view;
    }

    private void cargarData(Integer idUsuario) {
        String url = ValidSession.IP + "/ws_listarMisActividades.php?id_usuario=" + idUsuario;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        final EstadoMisActividadesResponse actividad = EstadoMisActividadesResponse.cargarDataDesdeJsonObject(jsonObject);
                        misActividades.add(actividad);
                    }
                    cargarAdaptador();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void cargarAdaptador() {
        EstadoMisActividadesAdapter adapter = new EstadoMisActividadesAdapter(misActividades, this);
        rvEstadoMisActividades.setAdapter(adapter);
    }

    @Override
    public void onEstadoActividadClick(int posicion) {
        EstadoMisActividadesResponse actividadSeleccionada = misActividades.get(posicion);
        if (actividadSeleccionada.getEstado().equals("Aceptado")) {
            // pasando data de fragment a fragment
            Integer id = actividadSeleccionada.getId();
            Fragment actividadAceptada = new ActividadAceptadaFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            actividadAceptada.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.containerFragments, actividadAceptada).commit();
        }
    }
}