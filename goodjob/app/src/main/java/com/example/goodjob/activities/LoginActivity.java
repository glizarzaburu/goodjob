package com.example.goodjob.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.Empresa;
import com.example.goodjob.classes.User;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.util.Certificado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private ImageButton btnIngresar;
    private TextView tvregister;
    private EditText txtUser, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        tvregister = findViewById(R.id.TvRegister);
        btnIngresar = findViewById(R.id.btnLogin);

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormRegisterUserActivity.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        Certificado.handleSSLHandshake();
    }

    private void iniciarSesion() {

        String url;
        if (txtUser.getText().toString().contains("@")) {
            url = ValidSession.IP + "/WS_Login.php?correo=" + txtUser.getText().toString().trim()
                    + "&pass=" + txtPass.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array = new JSONArray(response);
                        if (array.length() == 0) {
                            Toast.makeText(getApplicationContext(), R.string.incorrect_user, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        JSONObject jsonObject = array.getJSONObject(0);
                        loadUserDataFromDatabase(jsonObject);
                        int idActividad = getIntent().getIntExtra("idActividad", 0);
                        if (idActividad != 0) { // iniciando sesion desde detalle actividad
                            Intent intent = new Intent(getApplicationContext(), DetailsAndApplyActivity.class);
                            intent.putExtra("idActividad", idActividad);
                            startActivity(intent);
                            finish();
                        } else { // iniciando sesion de manera normal
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), R.string.incorrect_user, Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(getApplicationContext()).add(request);
        } else {
            url = ValidSession.IP + "/ws_loginEmpresa.php?ruc=" + txtUser.getText().toString().trim()
                    + "&pass=" + txtPass.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array = new JSONArray(response);
                        if (array.length() == 0) {
                            Toast.makeText(LoginActivity.this, "RUC y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        JSONObject data = array.getJSONObject(0);
                        ValidSession.empresaLogueada = Empresa.cargarDatosDesdeJson(data);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(getApplicationContext()).add(request);
        }
    }

    private void loadUserDataFromDatabase(JSONObject data) {
        ValidSession.usuarioLogueado = new User();
        ValidSession.usuarioLogueado.loadUserDataFromJsonObject(data);
    }
}