package com.example.goodjob.classes;

public class ValidSession {

    public static User usuarioLogueado = null;
    public static Empresa empresaLogueada = null;
//     private static final String LOCAL = "https://192.168.43.172";
    //  la línea debajo usa la conexión remota
    //private static final String LOCAL = "https://www.inmedia.pe/models"; // "https://anthostudio.ga";
    private static final String LOCAL = "https://goodjob-s601.000webhostapp.com/";
    public static final String IP = LOCAL + "/Conexiones";
    public static final String IP_IMAGENES = LOCAL + "/imagenes";
    public static final String IMAGENES_ACTIVIDADES = IP_IMAGENES + "/imagenes_actividades/";
    public static final String IMAGENES_PRODUCTOS = IP_IMAGENES + "/imagenes_productos/";
}