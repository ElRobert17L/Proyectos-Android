package mx.com.rlr.seccion_01_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    //Elementos UI
    private ImageButton messageBtn;
    private Button shareBtn;

    //Otros valores
    private String name;
    private int age;
    private int typeOfMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Activar flechas ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recogemos el nombre del activity anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            age = bundle.getInt("age");
            typeOfMessage = bundle.getInt("option");
        }

        //Instanciamos los elementos de la UI con sus referencias
        messageBtn = (ImageButton) findViewById(R.id.messageButton);
        shareBtn = (Button) findViewById(R.id.shareButton);

        //Evento click del boton confirmar
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, createMessage(name, age, typeOfMessage), Toast.LENGTH_LONG).show();
                shareBtn.setVisibility(View.VISIBLE);
                messageBtn.setVisibility(View.INVISIBLE);
            }
        });

        //Evento click boton Share
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, createMessage(name, age, typeOfMessage));
                startActivity(intent);
            }
        });
    }

    private String createMessage(String name, int age, int typeOfMessage) {
        if (typeOfMessage == SecondActivity.GREETER_OPTION) {
            return "Hola " + name + ", ¿Como llevas esos " + age + " años?";
        } else {
            return "Espero verte pronto " + name + ", antes que cumplas " + (age + 1);
        }
    }
}