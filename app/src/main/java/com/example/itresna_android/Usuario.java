package com.example.itresna_android;

public class Usuario {

    String cod_usuario;
    String tip_usuario;
    String cod_org;
    String sarbidea;
    String nombre;
    String ape1;
    String ape2;

    public Usuario(String cod_usuario, String tip_usuario, String cod_org, String sarbidea, String nombre, String ape1, String ape2){
        this.cod_usuario=cod_usuario;
        this.tip_usuario=tip_usuario;
        this.cod_org=cod_org;
        this.sarbidea=sarbidea;
        this.nombre=nombre;
        this.ape1=ape1;
        this.ape2=ape2;
    }



    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getTip_usuario() {
        return tip_usuario;
    }

    public void setTip_usuario(String tip_usuario) {
        this.tip_usuario = tip_usuario;
    }

    public String getCod_org() {
        return cod_org;
    }

    public void setCod_org(String cod_org) {
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
}
