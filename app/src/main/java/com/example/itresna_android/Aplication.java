package com.example.itresna_android;

import android.app.Application;

import java.util.ArrayList;

public class Aplication extends Application {
    public String Usuariologueado;
    String eslogan;
    ArrayList<Espacio> espacios = new ArrayList<>();
    public String espacioSeleccionado;
    public ArrayList<Cop> cops = new ArrayList<>();
    public String codOrg;
    ArrayList<Cop> copsFoto = new ArrayList<>();
    public String descripcionCop;
    public String codEspacio;
    public String decripcionEspacio;
    public String cod_cop;
    public String cod_senal;
    public void onCreate(){
        super.onCreate();

    }
}
