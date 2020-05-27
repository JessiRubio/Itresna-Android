package com.example.itresna_android;

/** Clase generica para las etiquetas. Contiene atributos, constructora y getters y setters **/

public class Espacio {

    String cod_org;
    String desc_esp;
    String ind_esp_curacion;
    String orden;
    String cod_esp;

    public Espacio(String cod_esp, String cod_org, String desc_esp, String ind_esp_curacion, String orden){
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.desc_esp=desc_esp;
        this.ind_esp_curacion=ind_esp_curacion;
        this.orden=orden;
    }

    public String getCod_org() {
        return cod_org;
    }

    public void setCod_org(String cod_org) {
        this.cod_org = cod_org;
    }

    public String getDesc_esp() {
        return desc_esp;
    }

    public void setDesc_esp(String desc_esp) {
        this.desc_esp = desc_esp;
    }

    public String getInd_esp_curacion() {
        return ind_esp_curacion;
    }

    public void setInd_esp_curacion(String ind_esp_curacion) {
        this.ind_esp_curacion = ind_esp_curacion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getCod_esp() {
        return cod_esp;
    }

    public void setCod_esp(String cod_esp) {
        this.cod_esp = cod_esp;
    }
}


