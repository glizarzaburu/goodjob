package com.example.goodjob.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.ValidSession;

import java.util.HashMap;
import java.util.Map;

public class SolicitudEmpresaActivity extends AppCompatActivity {

    private EditText nombreComercial, ruc, telefono,
            direccion, razonSocial, correo, codigoPostal;
    private CheckBox terminos, politicas;
    private Spinner distritos;
    private Button enviar;

    private TextInputLayout tilNombreComercial, tilRuc, tilTelefono,
            tilDireccion, tilRazonSocial, tilCorreo, tilCodigoPostal;

    private final int CAMPO_VACIO = 0;
    final int NOMBRE_COMERCIAL_TOPE_LONGITUD = 120;
    final int RAZON_SOCIAL_TOPE_LONGITUD = 120;
    final int RUC_LONGITUD = 11;
    final int DIRECCION_TOPE_LONGITUD = 150;
    final int CORREO_TOPE_LONGITUD = 50;
    final int TELEFONO__TOPE_LONGITUD = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_empresa);

        inicializarViews();
        establecerAdapterSpinnerDistritos();
        asignarEventoClickBotonEnviar();
        asignarEventoDeCheckeoDeCampos();
    }

    private void inicializarViews() {
        nombreComercial = findViewById(R.id.etNombreComercial);
        ruc = findViewById(R.id.etRuc);
        telefono = findViewById(R.id.etTelefonoEmpresa);
        direccion = findViewById(R.id.etDireccionEmpresa);
        razonSocial = findViewById(R.id.etRazonSocial);
        correo = findViewById(R.id.etCorreoEmpresa);
        codigoPostal = findViewById(R.id.etCodigoPostal);
        terminos = findViewById(R.id.chkTerminos);
        politicas = findViewById(R.id.chkPoliticas);
        distritos = findViewById(R.id.spinnerDistritoEmpresa);
        enviar = findViewById(R.id.btnSolicitarEmpresa);
        tilNombreComercial = findViewById(R.id.tilNombreComercial);
        tilRuc = findViewById(R.id.tilRuc);
        tilTelefono = findViewById(R.id.tilTelefonoEmpresa);
        tilDireccion = findViewById(R.id.tilDireccionEmpresa);
        tilRazonSocial = findViewById(R.id.tilRazonSocial);
        tilCorreo = findViewById(R.id.tilCorreoEmpresa);
        tilCodigoPostal = findViewById(R.id.tilCodigoPostal);
    }

    private void establecerAdapterSpinnerDistritos() {
        ArrayAdapter<CharSequence> adapterDistritos =
                ArrayAdapter.createFromResource(this,
                        R.array.distritos_array,
                        R.layout.item_spinner_publicar_actividad);
        distritos.setAdapter(adapterDistritos);
    }

    private void asignarEventoClickBotonEnviar() {
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarDatos()) {
                    return;
                }

                String url = ValidSession.IP + "/ws_solicitudEmpresa.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SolicitudEmpresaActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SolicitudEmpresaActivity.this, SolicitudEmpresaResultadoActivity.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SolicitudEmpresaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("nombre_comercial", nombreComercial.getText().toString());
                        params.put("ruc", ruc.getText().toString());
                        params.put("telefono", telefono.getText().toString());
                        params.put("direccion", direccion.getText().toString());
                        params.put("razon_social", razonSocial.getText().toString());
                        params.put("correo", correo.getText().toString());
                        params.put("codigo_postal", codigoPostal.getText().toString());
                        params.put("id_distrito", String.valueOf(distritos.getSelectedItemId()));
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        });
    }

    private boolean validarDatos() {
        return esRucValido(ruc.getText().toString())
                && esNombreComercialValido(nombreComercial.getText().toString())
                && esRazonSocialValido(razonSocial.getText().toString())
                && esCorreoValido(correo.getText().toString())
                && esTelefonoValido(telefono.getText().toString())
                && esCodigoPostalValido(codigoPostal.getText().toString())
                && esDireccionValido(direccion.getText().toString())
                && esDistritoValido(distritos)
                && estaCheckeado(terminos, "los terminos y condiciones")
                && estaCheckeado(politicas, "las politicas");
    }

    private boolean esNombreComercialValido(String nombreComercial) {
        if (nombreComercial.trim().length() == CAMPO_VACIO) {
            tilNombreComercial.setError("Debe ingresar un nombre comercial");
            return false;
        } else if (nombreComercial.trim().length() > NOMBRE_COMERCIAL_TOPE_LONGITUD) {
            tilNombreComercial.setError("No debe exceder de 120 caracteres");
            return false;
        }
        tilNombreComercial.setError(null);
        return true;
    }

    private boolean esRucValido(String ruc) {
        if (ruc.trim().length() == CAMPO_VACIO) {
            tilRuc.setError("Debe ingresar un RUC");
            return false;
        } else if (ruc.trim().length() != RUC_LONGITUD) {
            tilRuc.setError("El RUC consta de 11 digitos");
            return false;
        } else if (!ruc.trim().matches("^[0-9]+$")) {
            tilRuc.setError("Ingrese un RUC válido");
            return false;
        }
        tilRuc.setError(null);
        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (telefono.trim().length() == CAMPO_VACIO) {
            tilTelefono.setError("Debe ingresar un número telefonico");
            return false;
        } else if (telefono.trim().length() > TELEFONO__TOPE_LONGITUD) {
            tilTelefono.setError("El teléfono consta de 9 digitos");
            return false;

        } else if (!telefono.trim().matches("^[0-9]+$")) {
            tilTelefono.setError("Ingrese un telefono válido");
            return false;
        }
        tilTelefono.setError(null);
        return true;
    }

    private boolean esDireccionValido(String direccion) {
        if (direccion.trim().length() == CAMPO_VACIO) {
            tilDireccion.setError("Debe ingresar una dirección");
            return false;
        } else if (direccion.trim().length() > DIRECCION_TOPE_LONGITUD) {
            tilDireccion.setError("Dirección muy larga");
            return false;
        }
        tilDireccion.setError(null);
        return true;
    }

    private boolean esRazonSocialValido(String razonSocial) {
        if (razonSocial.trim().length() == CAMPO_VACIO) {
            tilRazonSocial.setError("Debe ingresar una razón social");
            return false;
        } else if (razonSocial.trim().length() > RAZON_SOCIAL_TOPE_LONGITUD) {
            tilRazonSocial.setError("Excedido tope de 120 caracteres");
            return false;
        }
        tilRazonSocial.setError(null);
        return true;
    }

    private boolean esCodigoPostalValido(String codigoPostal) {
        if (codigoPostal.trim().length() == CAMPO_VACIO) {
            tilCodigoPostal.setError("Debe ingresar un código postal");
            return false;
        } else if (!codigoPostal.trim().matches("^[0-9]+$")) {
            tilCodigoPostal.setError("Ingrese un código postal válido");
            return false;
        }
        tilCodigoPostal.setError(null);
        return true;
    }

    private boolean esCorreoValido(String correo) {
        if (correo.trim().length() == CAMPO_VACIO) {
            tilCorreo.setError("Debe ingresar un correo");
            return false;
        } else if (correo.trim().length() > CORREO_TOPE_LONGITUD) {
            tilCorreo.setError("El correo no debe exceder 50 caracteres");
            return false;
        } else if (!correo.trim().matches("^([\\w]\\.?)+@[\\w]+(\\.[\\w]+)+$")) {
            tilCorreo.setError("Ingrese un correo válido");
            return false;
        }

        tilCorreo.setError(null);
        return true;
    }

    private boolean estaCheckeado(CheckBox checkBox, String condicionAAceptar) {
        if (!checkBox.isChecked()) {
            Toast.makeText(this, "Debes aceptar " + condicionAAceptar, Toast.LENGTH_SHORT).show();
        }
        return checkBox.isChecked();
    }

    private boolean esDistritoValido(Spinner spinner) {
        if (spinner.getSelectedItemId() == 0) {
            Toast.makeText(this, "Selecciona un distrito", Toast.LENGTH_SHORT).show();
        }
        return spinner.getSelectedItemId() != 0;
    }

    private void asignarEventoDeCheckeoDeCampos() {
        nombreComercial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esNombreComercialValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ruc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esRucValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esTelefonoValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        direccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esDireccionValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        razonSocial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esRazonSocialValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esCorreoValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        codigoPostal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esCodigoPostalValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}