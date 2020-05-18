package com.example.itresna_android.cops;

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

    String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreCop(String nombreCop) {
        this.nombreCop = nombreCop;
    }

    String getNombreCop() {
        return nombreCop;
    }

    public void setSenal(String senal) {
        this.senal = senal;
    }

    String getSenal() {
        return senal;
    }
}
