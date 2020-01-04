package com.example.goodjob.classes;

public class ProductoCanje {

    private Integer id, stock;
    private String producto, imagen;
    private Double valor;

    public ProductoCanje() {
    }

    public ProductoCanje(Integer id, Integer stock, String producto, String imagen, Double valor) {
        this.id = id;
        this.stock = stock;
        this.producto = producto;
        this.imagen = imagen;
        this.valor = valor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
