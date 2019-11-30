package com.example.goodjob.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.util.Certificado;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormRegisterUserActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Button btnRegister, btnCancel;
    private EditText tvcorreo, tvpass, tvpassdos;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Date date = new Date();
    String fecharegistro = dateFormat.format(date);

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register_user);


        tvpass = findViewById(R.id.txtpassR);
        tvpassdos = findViewById(R.id.txtpassdosR);


        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btn_Cancel);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarWebServiceRegistrarUser();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectedUserActivity.class);
                startActivity(intent);
            }
        });
        Certificado.handleSSLHandshake();
    }

    private void CargarWebServiceRegistrarUser() {


        String url = ValidSession.IP + "/WS_RegistrarUsuario.php?Unombre=" + tvcorreo.getText().toString() + "&" +
                "Upass=" + tvpass.getText().toString() + "&" +
                "UfechaRegistro=" + fecharegistro + "&" +
                "Uestado=1";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Oh! será el fin del hombre araña?" + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Te has Registrado con Exito", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}