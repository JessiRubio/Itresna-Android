package com.example.itresna_android;

public class ConexionBD {


    // En local
    private static final String ROOT_URL = "http://192.168.56.1:8080/Android/v1/";

    //En server
    //private static final String ROOT_URL = "http://itresna.fptxurdinaga.in/Android/v1/";

    // PHP para hacer el login
    public static final String URL_LOGIN = ROOT_URL+"Login.php";

    //PHP que carga las organizaciones
    public static final String URL_Org = ROOT_URL+"org.php";

    //PHP qur carga los espacios
    public static final String URL_Esp = ROOT_URL+"espacio.php";

    // Operaciones de prueba
    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";






}
