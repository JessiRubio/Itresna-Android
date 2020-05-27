package com.example.itresna_android;


/** Clase generica para los permisos. Contiene atributos, constructora y getters y setters **/
public class Permisos {

    String cod_usuario;
    String cod_cop;
    String cod_esp;
    String cod_org;
    String ind_admin;

    public Permisos(String cod_usuario, String cod_cop, String cod_esp, String cod_org, String ind_admin){
        this.cod_usuario=cod_usuario;
        this.cod_cop=cod_cop;
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.ind_admin=ind_admin;

    }


    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getCod_cop() {
        return cod_cop;
    }

    public void setCod_cop(String cod_cop) {
        this.cod_cop = cod_cop;
    }

    public String getCod_esp() {
        return cod_esp;
    }

    public void setCod_esp(String cod_esp) {
        this.cod_esp = cod_esp;
    }

    public String getCod_org() {
        return cod_org;
    }

    public void setCod_org(String cod_org) {
        this.cod_org = cod_org;
    }

    public String getInd_admin() {
        return ind_admin;
    }

    public void setInd_admin(String ind_admin) {
        this.ind_admin = ind_admin;
    }
}
