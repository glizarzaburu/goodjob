package com.example.goodjob.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.util.Certificado;

import java.util.HashMap;
import java.util.Map;

public class FormRegisterUserActivity extends AppCompatActivity {
    private Button btnRegister, btnCancel;
    private EditText correo, password, nombre, paterno, materno;
    private TextView solicitar_cuenta_empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register_user);

        mapearViews();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarWebServiceRegistrarUser();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        solicitar_cuenta_empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormRegisterUserActivity.this, SolicitudEmpresaActivity.class));
            }
        });
        Certificado.handleSSLHandshake();
    }

    private void mapearViews() {
        nombre = findViewById(R.id.etNombreRegistro);
        paterno = findViewById(R.id.etPaternoRegistro);
        materno = findViewById(R.id.etMaternoRegistro);
        correo = findViewById(R.id.etCorreoRegistro);
        password = findViewById(R.id.etPasswordRegistro);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btn_Cancel);
        solicitar_cuenta_empresa = findViewById(R.id.solicitar_cuenta_empresa);
    }

    private void CargarWebServiceRegistrarUser() {
        String url = ValidSession.IP + "/ws_registrarUsuario.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FormRegisterUserActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FormRegisterUserActivity.this, LoginActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FormRegisterUserActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre.getText().toString().trim());
                params.put("paterno", paterno.getText().toString().trim());
                params.put("materno", materno.getText().toString().trim());
                params.put("correo", correo.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }
}