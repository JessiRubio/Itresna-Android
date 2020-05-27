package com.example.itresna_android;

/** Clase generica para las etiquetas. Contiene atributos, constructora y getters y setters **/
public class Etiqueta {

    String cod_etiqueta;
    String cod_cop;
    String cod_esp;
    String cod_org;
    String desc_etiqueta;

    public Etiqueta(String cod_etiqueta, String cod_cop, String cod_esp, String cod_org, String desc_etiqueta){
        this.cod_etiqueta=cod_etiqueta;
        this.cod_cop=cod_cop;
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.desc_etiqueta=desc_etiqueta;

    }


    public String getCod_etiqueta() {
        return cod_etiqueta;
    }

    public void setCod_etiqueta(String cod_etiqueta) {
        this.cod_etiqueta = cod_etiqueta;
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

    public String getDesc_etiqueta() {
        return desc_etiqueta;
    }

    public void setDesc_etiqueta(String desc_etiqueta) {
        this.desc_etiqueta = desc_etiqueta;
    }
}
