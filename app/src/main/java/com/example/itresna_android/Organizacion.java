package com.example.itresna_android;

/** Clase generica para las organizaciones. Contiene atributos, constructora y getters y setters **/

public class Organizacion {

    String cod_org;
    String desc_org;
    String img_org;
    String enlace_org;
    String eslogan_org;

    public Organizacion (String cod_org, String desc_org, String img_org, String enlace_org, String eslogan_org){
        this.cod_org=cod_org;
        this.desc_org=desc_org;
        this.img_org=img_org;
        this.enlace_org=enlace_org;
        this.eslogan_org=eslogan_org;

    }


    public String getCod_org() {
        return cod_org;
    }

    public void setCod_org(String cod_org) {
        this.cod_org = cod_org;
    }

    public String getDesc_org() {
        return desc_org;
    }

    public void setDesc_org(String desc_org) {
        this.desc_org = desc_org;
    }

    public String getImg_org() {
        return img_org;
    }

    public void setImg_org(String img_org) {
        this.img_org = img_org;
    }

    public String getEnlace_org() {
        return enlace_org;
    }

    public void setEnlace_org(String enlace_org) {
        this.enlace_org = enlace_org;
    }

    public String getEslogan_org() {
        return eslogan_org;
    }

    public void setEslogan_org(String eslogan_org) {
        this.eslogan_org = eslogan_org;
    }
}
