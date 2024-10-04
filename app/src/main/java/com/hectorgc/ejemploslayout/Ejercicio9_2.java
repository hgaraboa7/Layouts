package com.hectorgc.ejemploslayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio9_2 extends AppCompatActivity {

    private EditText apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio92);

        TextView saludo=findViewById(R.id.textView6);
        Button cerrar=findViewById(R.id.botoncerrar);
        apellido = findViewById(R.id.apellido);

        //obtener nombre
        //usamos la misma clave que se pasó en el intent para poder acceder a su valor
        String name= getIntent().getStringExtra("NOMBRE");
        saludo.setText("Hola "+ name+"!!!!");

        cerrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String apelido=apellido.getText().toString();
                Intent resultado= new Intent();
                //enviamos clave valor para enviar los apellidos
                resultado.putExtra("APELLIDO", apelido);
                //establecemos el result_ok y enviamos los el intent con los apellidos
                setResult(RESULT_OK, resultado);

                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}