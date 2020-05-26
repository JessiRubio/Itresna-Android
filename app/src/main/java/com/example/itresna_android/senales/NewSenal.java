package com.example.itresna_android.Senales;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.itresna_android.R;
import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;

public class NewSenal extends Activity {

    private Button btn_cancelar;
    private Button btn_Anadir;

    private EditText url;
    private EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_senal);

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height/2));

        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //Funcionalidades pop up
        btn_cancelar = findViewById(R.id.btnCancelarSenal);
        btn_Anadir = findViewById(R.id.btnAnadirSenal);

        url = findViewById(R.id.urlEntregada);
        descripcion = findViewById(R.id.descripcionEntregada);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_Anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the TextCrawler to parse your url into a preview.
                TextCrawler textCrawler = new TextCrawler();
                // ..
                // Create the callbacks to handle pre and post exicution of the preview generation.
                LinkPreviewCallback linkPreviewCallback = new LinkPreviewCallback() {
                    @Override
                    public void onPre() {
                        // Any work that needs to be done before generating the preview. Usually inflate
                        // your custom preview layout here.
                    }

                    @Override
                    public void onPos(SourceContent sourceContent, boolean b) {
                        // Populate your preview layout with the results of sourceContent.
                    }
                };

                textCrawler.makePreview( linkPreviewCallback,url.getText().toString());
            }
        });
    }
}
