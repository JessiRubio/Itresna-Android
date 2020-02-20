package com.example.itresna_android;

public class Usuarios {
    String cod_usuario;
    int tip_usuario;
    int cod_org;

    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public int getTip_usuario() {
        return tip_usuario;
    }

    public void setTip_usuario(int tip_usuario) {
        this.tip_usuario = tip_usuario;
    }

    public int getCod_org() {
        return cod_org;
    }

    public void setCod_org(int cod_org) {
        this.cod_org = cod_org;
    }

    public String getSarbidea() {
        return sarbidea;
    }

    public void setSarbidea(String sarbidea) {
        this.sarbidea = sarbidea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getCampo_clasificador1() {
        return campo_clasificador1;
    }

    public void setCampo_clasificador1(String campo_clasificador1) {
        this.campo_clasificador1 = campo_clasificador1;
    }

    public String getCampo_clasificador2() {
        return campo_clasificador2;
    }

    public void setCampo_clasificador2(String campo_clasificador2) {
        this.campo_clasificador2 = campo_clasificador2;
    }

    public String getCampo_clasificador3() {
        return campo_clasificador3;
    }

    public void setCampo_clasificador3(String campo_clasificador3) {
        this.campo_clasificador3 = campo_clasificador3;
    }

    String sarbidea;
    String nombre;
    String ape1;
    String ape2;
    String campo_clasificador1;
    String campo_clasificador2;
    String campo_clasificador3;

    public void Usuarios (String cod_usuario ,int tip_usuario, int cod_org, String sarbidea, String nombre , String ape1 , String ape2 ,
                         String campo_clasificador1, String campo_clasificador2, String campo_clasificador3){
        this.cod_usuario = cod_usuario;
        this.tip_usuario = tip_usuario;
        this.cod_org = cod_org;
        this.sarbidea = sarbidea;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.campo_clasificador1 = campo_clasificador1;
        this.campo_clasificador2 = campo_clasificador2;
        this.campo_clasificador3 = campo_clasificador3;
    }

}
