package com.example.goodjob.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.Actividad;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.fragments.DetalleMiPublicacionFragment;
import com.example.goodjob.util.Certificado;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class DetailsAndApplyActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView author;
    private TextView creationDate;
    private TextView endDate;
    private TextView currentParticipants;
    private TextView requiredParticipants;
    private ImageView photo;
    private Button postular;
    private TextView reward;
    private TextView distrito;
    private Actividad selectedActivity;
    Actividad actividad = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_and_apply);

        title = findViewById(R.id.tvTitle);
        description = findViewById(R.id.tvDescription);
        author = findViewById(R.id.tvAuthorName);
        creationDate = findViewById(R.id.tvCreationDateValue);
        endDate = findViewById(R.id.tvendDateValue);
        currentParticipants = findViewById(R.id.tvCurrent);
        requiredParticipants = findViewById(R.id.tvRequired);
        photo = findViewById(R.id.imgPhoto);
        postular = findViewById(R.id.btnMoreInfo);
        reward = findViewById(R.id.tvReward);
        distrito = findViewById(R.id.tvDistritoValue);

        selectedActivity = getIntent().getExtras().getParcelable("selectedActivity");
        if (selectedActivity != null) {
            loadData(selectedActivity);
        }

        final int idActividad = getIntent().getIntExtra("idActividad", 0);
        if (idActividad != 0) {
            String url = ValidSession.IP + "/ws_listarActividadSeleccionadaPorLogin.php?id_actividad=" + idActividad;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array = new JSONArray(response);
                        JSONObject jsonObject = array.getJSONObject(0);
                        actividad = Actividad.loadActivityDataFromJsonObject(jsonObject);
                        loadData(actividad);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            Volley.newRequestQueue(getApplicationContext()).add(request);
        }

        postular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateStartedSession()) {
                    if (selectedActivity != null) {
                        realizarPostulacion(ValidSession.usuarioLogueado.getId(), selectedActivity.getId());
                        cambioDeEstadoBoton();
                        incrementarParticipantes(selectedActivity.getCurrentParticipants() + 1, selectedActivity.getId());

                    } else {
                        realizarPostulacion(ValidSession.usuarioLogueado.getId(), idActividad);
                        cambioDeEstadoBoton();
                        incrementarParticipantes(actividad.getCurrentParticipants() + 1, idActividad);
                    }
                    startActivity(new Intent(DetailsAndApplyActivity.this, MainActivity.class));
                    return;
                }
                dialogMessage("Inicio de sesión", R.string.should_start_session,
                        "Iniciar sesión!", LoginActivity.class);
            }
        });

        if (ValidSession.usuarioLogueado != null)
            if (selectedActivity != null) {
                consultarSiYaPostulo(ValidSession.usuarioLogueado.getId(), selectedActivity.getId());
            } else {
                consultarSiYaPostulo(ValidSession.usuarioLogueado.getId(), idActividad);
            }
        else if (ValidSession.empresaLogueada != null)
            empresasNoPostulan();

        Certificado.handleSSLHandshake();
    }

    private void loadData(Actividad actividad) {
        title.setText(actividad.getTitle());
        description.setText(actividad.getDescription());
        author.setText(actividad.getAuthor());
        creationDate.setText(formatDate(actividad.getCreationDate()));
        endDate.setText(formatDate(actividad.getEndDate()));
        currentParticipants.setText("De momento hay " + actividad.getCurrentParticipants() + " postulantes");
        requiredParticipants.setText("Se necesitan " + actividad.getRequiredParticipants() + " personas");
        distrito.setText(actividad.getDistrito());
        reward.setText(actividad.getRewardType() + " : " + actividad.getReward());
        ImageRequest request = new ImageRequest(ValidSession.IMAGENES_ACTIVIDADES + actividad.getPhoto(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                photo.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsAndApplyActivity.this, "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    private boolean validateStartedSession() {
        return ValidSession.usuarioLogueado != null;
    }

    private void dialogMessage(String title, Integer message, String positivo, final Class<?> next) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAndApplyActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positivo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DetailsAndApplyActivity.this, next);
                        intent.putExtra("idActividad", selectedActivity.getId());
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String formatDate(String date) {
        String[] fechaEnPartes = date.split("-");
        return fechaEnPartes[2] + "/" + fechaEnPartes[1] + "/" + fechaEnPartes[0];
    }

    private void consultarSiYaPostulo(Integer idUsuario, Integer idActividad) {
        String url = ValidSession.IP + "/ws_consultarPostulacionUsuario.php?id_usuario=" + idUsuario + "&id_actividad=" + idActividad;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Integer id = jsonObject.optInt("id");

                        if (id != null)
                            cambioDeEstadoBoton();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void realizarPostulacion(Integer idUsuario, Integer idActividad) {
        String url = ValidSession.IP + "/ws_postulacionActividad.php?id_usuario=" + idUsuario + "&id_actividad=" + idActividad;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    private void cambioDeEstadoBoton() {
        postular.setText(R.string.already_in);
        postular.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.rojo));
        postular.setEnabled(false);
    }

    private void empresasNoPostulan() {
        postular.setText(R.string.empresas_no_postulan);
        postular.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
        postular.setEnabled(false);
    }

    private void incrementarParticipantes(final Integer cantidad, Integer idActividad) {
        String url = ValidSession.IP + "/ws_incrementarParticipantesPostulacion.php?cantidad=" + cantidad + "&id_actividad=" + idActividad;

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
                    DetailsAndApplyActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentParticipants.setText("De momento hay " + cantidad + " postulantes");
                        }
                    });
                }
            }
        });
    }
}