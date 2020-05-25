package com.example.itresna_android;

public class ConexionBD {


    // En local
    //private static final String ROOT_URL = "http://192.168.56.1:8080/Android/v1/";

    //En server
    private static final String ROOT_URL = "http://itresna.fptxurdinaga.in/Android/v1/";

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

    //PHP que carga las etiquetas
    public static final String URL_Etiqueta = ROOT_URL+"etiqueta.php";

    //PHP que carga los likes
    public static final String URL_Likes = ROOT_URL+"likes.php";

    //PHP que borra señales
    public static final String URL_BorrarSenal = ROOT_URL+"borrarSenal.php";

    //PHP que modifica señales
    public static final String URL_ModificarSenal = ROOT_URL+"modificarSenal.php";

    //PHP que crea likes
    public static final String URL_CrearLike = ROOT_URL+"crearLike.php";

    //PHP que borra likes
    public static final String URL_BorrarLike = ROOT_URL+"borrarLike.php";

    //PHP que recupera contraseña
    public static final String URL_RecuperarContrasena = ROOT_URL+"recuperarContrasena.php";



    // Operaciones de prueba
    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";






}
