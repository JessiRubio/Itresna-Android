package com.example.itresna_android;

public class Cop {

    String cod_cop;
    String cod_esp;
    String cod_org;
    String desc_cop;
    String img_cop;
    //String ind_cop_graficos;

    public Cop (String cod_cop, String cod_esp, String cod_org, String desc_cop, String img_cop){

        this.cod_cop=cod_cop;
        this.cod_esp=cod_esp;
        this.cod_org=cod_org;
        this.desc_cop=desc_cop;
        this.img_cop=img_cop;

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

    public String getDesc_cop() {
        return desc_cop;
    }

    public void setDesc_cop(String desc_cop) {
        this.desc_cop = desc_cop;
    }

    public String getImg_cop() {
        return img_cop;
    }

    public void setImg_cop(String img_cop) {
        this.img_cop = img_cop;
    }
}
