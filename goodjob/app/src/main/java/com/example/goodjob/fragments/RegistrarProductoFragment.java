package com.example.goodjob.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.R;
import com.example.goodjob.classes.ValidSession;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class RegistrarProductoFragment extends Fragment {

    private EditText producto, valor, stock, lugarCanje;
    private ImageView imagen;
    private Button registrar;
    private TextInputLayout tilProducto, tilValor, tilStock,
            tilLugarCanje;
    private Bitmap bitmap;

    private final int CAMPO_VACIO = 0;
    final int NOMBRE_PRODUCTO_TOPE_LONGITUD = 40;
    final int LUGAR_CANJE_TOPE_LONGITUD = 250;
    final int STOCK_MINIMO = 1;
    final int REQUEST_CODE_GALLERY = 78;

    public RegistrarProductoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrar_producto, container, false);

        mapearViews(view);
        clickEnBotonRegistrar();
        checkeoDeCampos();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        clickEnImagen();
    }

    private void mapearViews(View v) {
        producto = v.findViewById(R.id.etNombreProductoRegistro);
        valor = v.findViewById(R.id.etValorProductoRegistro);
        stock = v.findViewById(R.id.etCantidadProductoRegistro);
        lugarCanje = v.findViewById(R.id.etLugarCanje);
        imagen = v.findViewById(R.id.ivImagenProductoRegistro);
        tilProducto = v.findViewById(R.id.tilNombreProducto);
        tilValor = v.findViewById(R.id.tilValorProducto);
        tilStock = v.findViewById(R.id.tilCantidadProductoRegistro);
        tilLugarCanje = v.findViewById(R.id.tilLugarCanje);
        registrar = v.findViewById(R.id.btnRegistrarProducto);
    }

    private void clickEnImagen() {
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermisosDeLectura();
            }
        });
    }

    private void clickEnBotonRegistrar() {
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarDatos()) return;

                String url = ValidSession.IP_IMAGENES + "/ws_registrarProducto.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        cargarFragmentProductosEmpresa(0);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("producto", producto.getText().toString().trim());
                        params.put("valor", valor.getText().toString().trim());
                        params.put("stock", stock.getText().toString().trim());
                        params.put("id_empresa", String.valueOf(ValidSession.empresaLogueada.getId()));
                        params.put("imagen", imageToString(bitmap));
                        params.put("lugar_canje", lugarCanje.getText().toString().trim());
                        return params;
                    }
                };
                Volley.newRequestQueue(getContext()).add(request);
            }
        });
    }

    private void cargarFragmentProductosEmpresa(int estado) {
        Fragment fragment = new ProductoEsperaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("estado", estado);
        fragment.setArguments(bundle);
        cargarFragment(fragment);
    }
    private void cargarFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, fragment)
                .commit();
    }

    private boolean validarDatos() {
        return esProductoValido(producto.getText().toString().trim())
                && esStockValido(stock.getText().toString().trim())
                && esValorValido(valor.getText().toString().trim())
                && esLugarCanjeValido(lugarCanje.getText().toString().trim());
    }

    private void checkeoDeCampos() {
        producto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esProductoValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        stock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esStockValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esValorValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lugarCanje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esLugarCanjeValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean esProductoValido(String nombreProducto) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (nombreProducto.length() == CAMPO_VACIO) {
            tilProducto.setError("Debe ingresar un nombre");
            return false;
        } else if (!patron.matcher(nombreProducto).matches()) {
            tilProducto.setError("Ingrese un nombre valido");
            return false;
        } else if (nombreProducto.length() > NOMBRE_PRODUCTO_TOPE_LONGITUD) {
            tilProducto.setError("No puede exceder 40 caracteres");
            return false;
        }
        tilProducto.setError(null);
        return true;
    }

    private boolean esStockValido(String stock) {
        if (stock.length() == CAMPO_VACIO) {
            tilStock.setError("Ingrese stock");
            return false;
        } else if (!stock.matches("^[0-9]+$")) {
            tilStock.setError("Stock no valido");
            return false;
        } else if (Integer.valueOf(stock) < STOCK_MINIMO) {
            tilStock.setError("Stock mìnimo: 1");
            return false;
        }
        tilStock.setError(null);
        return true;
    }

    private boolean esValorValido(String valor) {
        if (valor.length() == CAMPO_VACIO) {
            tilValor.setError("Ingrese un valor(S/.)");
            return false;
        } else if (!valor.matches("^[0-9]+$")) {
            tilValor.setError("No es un valor valido");
            return false;
        } else if (Integer.valueOf(valor) < 0) {
            tilValor.setError("No es un valor valido");
            return false;
        }
        tilValor.setError(null);
        return true;
    }

    private boolean esLugarCanjeValido(String lugarCanje) {
        if (lugarCanje.length() == CAMPO_VACIO) {
            tilLugarCanje.setError("Debe ingresar un lugar de canje");
            return false;
        } else if (lugarCanje.length() > LUGAR_CANJE_TOPE_LONGITUD) {
            tilLugarCanje.setError("No puede exceder 250 caracteres");
            return false;
        }
        tilLugarCanje.setError(null);
        return true;
    }

    private void solicitarPermisosDeLectura() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(getContext(), "Permisos no concedidos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            try {
                setearImagenSeleccionada(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setearImagenSeleccionada(Intent data) throws FileNotFoundException {
        InputStream input = getActivity().getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(input);
        imagen.setImageBitmap(bitmap);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imgBytes = baos.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
