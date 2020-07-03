package com.example.manejoimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean activo1 = true;

    public void cambiarImagen(View view){
        Log.i("info", "boton presionado");

        ImageView imageView = findViewById(R.id.imageView);

        if (activo1){
            imageView.setImageResource(R.drawable.css3);
            activo1 = false;
        } else {
            imageView.setImageResource(R.drawable.html);
            activo1 = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}