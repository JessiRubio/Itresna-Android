package com.example.itresna_android;

import android.app.Application;

import java.util.ArrayList;

public class Aplication extends Application {
    String eslogan;
    ArrayList<Espacio> espacios = new ArrayList<>();
    String espacioSeleccionado;
    public ArrayList<Cop> cops = new ArrayList<>();
    public String codOrg;
    ArrayList<Cop> copsFoto = new ArrayList<>();
    public String descripcionCop;
    public String codEspacio;
    public String decripcionEspacio;
    public void onCreate(){
        super.onCreate();

    }
}
