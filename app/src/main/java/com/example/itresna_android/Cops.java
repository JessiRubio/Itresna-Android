package com.example.itresna_android;

public class Cops {
    private String nombreImagen;
    private String nombreCop;
    private String senal;

    public Cops(String nombreImagen, String nombreCop, String senal){
        this.nombreImagen = nombreImagen;
        this.nombreCop = nombreCop;
        this.senal = senal;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreCop(String nombreCop) {
        this.nombreCop = nombreCop;
    }

    public String getNombreCop() {
        return nombreCop;
    }

    public void setSenal(String senal) {
        this.senal = senal;
    }

    public String getSenal() {
        return senal;
    }
}
