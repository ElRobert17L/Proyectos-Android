package com.example.convertidormonedas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void enviar(View view){
        float pesos;
        EditText editText = (EditText) findViewById(R.id.pesosEditText);
        TextView textView = (TextView) findViewById(R.id.dolaresTextView);

        pesos = (float) (Float.parseFloat(editText.getText().toString()) * 0.045);
        String myPesos = Float.toString(pesos);
        textView.setText(myPesos + " dolares");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}