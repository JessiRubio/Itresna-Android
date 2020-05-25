package com.example.itresna_android;

import android.app.Application;

import java.util.ArrayList;

public class Aplication extends Application {
    String eslogan;
    ArrayList<Espacio> espacios = new ArrayList<>();
    public String espacioSeleccionado;
    public String nombreEspacioSeleccionados;
    public ArrayList<Cop> cops = new ArrayList<>();
    ArrayList<Cop> copsFoto = new ArrayList<>();
    public String nombreCopSeleccionada;
    public String codOrg;


    public void onCreate(){
        super.onCreate();

    }
}
