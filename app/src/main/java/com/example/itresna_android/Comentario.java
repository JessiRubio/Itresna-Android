package com.example.itresna_android;

public class Comentario {

    String cod_comentario;
    String cod_senal;
    String cod_cop;
    String cod_esp;
    String cod_org;
    String cod_usuario;
    String comentario;

    public Comentario(String cod_comentario, String cod_senal, String cod_cop, String cod_esp, String cod_org,String cod_usuario, String comentario){

        this.cod_comentario=cod_comentario;
        this.cod_senal=cod_senal;
        this.cod_cop=cod_cop;
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.cod_usuario=cod_usuario;
        this.comentario=comentario;

    }
}
