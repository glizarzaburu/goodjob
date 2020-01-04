package com.example.goodjob.classes;

import android.view.View;

import org.json.JSONObject;

public class EstadoMisActividadesResponse {

    private Integer id;
    private String titulo;
    private String autor;
    private String fecha;
    private String estado;
    private  String distrito;
    private View item;

    public static EstadoMisActividadesResponse cargarDataDesdeJsonObject(JSONObject data) {
        EstadoMisActividadesResponse miActividad = new EstadoMisActividadesResponse();

        miActividad.id = data.optInt("id");
        miActividad.titulo = data.optString("titulo");
        miActividad.fecha = data.optString("fecha_fin");
        Integer estado = data.optInt("estado");
        if (estado == 1)
            miActividad.estado = "En Espera";
        else if (estado == 2)
            miActividad.estado = "Aceptado";
        else if (estado == 3)
            miActividad.estado = "Rechazado";

        miActividad.distrito=data.optString("distrito");

        return miActividad;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getDistrito() {return distrito; }

    public View getItem() {
        return item;
    }

    public void setItem(View item) {
        this.item = item;
    }
}