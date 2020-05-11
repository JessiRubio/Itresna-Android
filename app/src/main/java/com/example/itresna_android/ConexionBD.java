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

    //PHP que carga los espacios
    public static final String URL_Esp = ROOT_URL+"espacio.php";

    //PHP que carga las cops
    public static final String URL_Cop = ROOT_URL+"cop.php";

    //PHP que carga las señales
    public static final String URL_Senal = ROOT_URL+"senal.php";

    //PHP que carga los comentarios
    public static final String URL_Comentario = ROOT_URL+"comentario.php";

    //PHP que carga los permisos
    public static final String URL_Permisos = ROOT_URL+"permisos.php";



    // Operaciones de prueba
    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";






}
