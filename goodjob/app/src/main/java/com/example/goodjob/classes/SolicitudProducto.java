package com.example.goodjob.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class SolicitudProducto implements Parcelable {
    private Integer id;
    private String nombre;
    private Integer stock;
    private Double valor;
    private String empresa;
    private String lugarCanje;
    private String imagenUrl;

    public SolicitudProducto() {
    }

    protected SolicitudProducto(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombre = in.readString();
        if (in.readByte() == 0) {
            stock = null;
        } else {
            stock = in.readInt();
        }
        if (in.readByte() == 0) {
            valor = null;
        } else {
            valor = in.readDouble();
        }
        empresa = in.readString();
        lugarCanje = in.readString();
        imagenUrl = in.readString();
    }

    public static final Creator<SolicitudProducto> CREATOR = new Creator<SolicitudProducto>() {
        @Override
        public SolicitudProducto createFromParcel(Parcel in) {
            return new SolicitudProducto(in);
        }

        @Override
        public SolicitudProducto[] newArray(int size) {
            return new SolicitudProducto[size];
        }
    };

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(nombre);
        if (stock == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(stock);
        }
        if (valor == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(valor);
        }
        parcel.writeString(empresa);
        parcel.writeString(lugarCanje);
        parcel.writeString(imagenUrl);
    }
}
