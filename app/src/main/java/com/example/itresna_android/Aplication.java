package com.example.itresna_android;

import android.app.Application;

import java.util.ArrayList;

public class Aplication extends Application {
    String eslogan;
    ArrayList<Espacio> espacios = new ArrayList<>();
    String espacioSeleccionado;
    public ArrayList<Cop> cops = new ArrayList<>();

    public void onCreate(){
        super.onCreate();
    }
}
