package com.example.itresna_android;

import java.net.URL;
/** Clase generica para las senales. Contiene atributos, constructora y getters y setters **/

public class Senal {

    String cod_senal;
    String cod_cop;
    String cod_esp;
    String cod_org;
    String cod_etiqueta;
    String cod_usuario;
    String desc_senal;
    String enlace;
    String fecha_hora;
    String img_senal;
    String titulo;

    public Senal(String cod_senal, String cod_cop, String cod_esp, String cod_org,
                 String cod_etiqueta, String cod_usuario, String desc_senal, String enlace, String fecha_hora, String img_senal, String titulo){
        this.cod_senal=cod_senal;
        this.cod_cop=cod_cop;
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.cod_etiqueta=cod_etiqueta;
        this.cod_usuario=cod_usuario;
        this.desc_senal=desc_senal;
        this.enlace=enlace;
        this.fecha_hora=fecha_hora;
        this.img_senal=img_senal;
        this.titulo=titulo;

    }


}
