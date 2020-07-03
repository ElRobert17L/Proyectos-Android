package mx.com.rlr.seccion_01_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    //Elementos UI
    private SeekBar seekBarAge;
    private TextView textViewAge;
    private Button nextBtn;
    private RadioButton radioButtonGreeter;
    private RadioButton radioButtonFarewell;

    //Otros valores
    private String name = "";
    private int age = 18;
    private final int MAX_AGE = 100;
    private final int MIN_AGE = 16;

    //Para compartir
    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Activar flechas ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recogemos el nombre del activity anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
        }

        //Instanciamos los elementos de la UI con sus referencias
        seekBarAge = (SeekBar) findViewById(R.id.edadSeekBar);
        textViewAge = (TextView) findViewById(R.id.edadTextView);
        nextBtn = (Button) findViewById(R.id.nextButton);
        radioButtonGreeter = (RadioButton) findViewById(R.id.greeterRadioButton);
        radioButtonFarewell = (RadioButton) findViewById(R.id.farewellRadioButton2);

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean fromUser) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Aunque no lo sobreescribamos con alguna funcionalidad, OnSeekBarChangeListener es una interfaz
                //y como interfaz que es, necesitamos sobreescribir todos sus metodos, aunque lo dejemos vacio.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Declaramos nuestras restricciones de edad en el evento en que el usuario suelta/deja el seekbar
                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if (age > MAX_AGE) {
                    nextBtn.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is: " + MAX_AGE +  " years old", Toast.LENGTH_LONG).show();
                } else if (age < MIN_AGE) {
                    nextBtn.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is: " + MIN_AGE +  " years old", Toast.LENGTH_LONG).show();
                } else {
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //Evento click del boton para pasar al siguiente Activity
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acceder al segundo activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                //Si el boton de greeter esta activo, option valdra 1, si no, 2
                int option = (radioButtonGreeter.isChecked()) ? GREETER_OPTION : FAREWELL_OPTION;
                intent.putExtra("option", option);
                startActivity(intent);
            }
        });
    }
}