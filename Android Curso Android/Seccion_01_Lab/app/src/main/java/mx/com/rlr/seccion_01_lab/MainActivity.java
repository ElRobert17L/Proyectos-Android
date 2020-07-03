package mx.com.rlr.seccion_01_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forzar y cargar icono en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Instanciamos los elementos de la UI con sus referencias
        btn = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.usuarioEditText);

        //Evento click del boton para pasar al siguiente Activity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acceder al segundo activity
                String nombreEditText = editText.getText().toString();
                if(nombreEditText != null && !nombreEditText.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("name", nombreEditText);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Ingresa tu nombre", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}