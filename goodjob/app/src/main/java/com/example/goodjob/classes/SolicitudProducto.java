package com.example.goodjob.classes;

public class SolicitudProducto {
    private Integer id;
    private String nombre;
    private Integer stock;
    private Double valor;
    private String empresa;
    private String lugarCanje;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getLugarCanje() {
        return lugarCanje;
    }

    public void setLugarCanje(String lugarCanje) {
        this.lugarCanje = lugarCanje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
