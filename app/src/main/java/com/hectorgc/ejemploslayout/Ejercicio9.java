package com.hectorgc.ejemploslayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio9 extends AppCompatActivity {


    private EditText nombre;
    private CheckBox destruir;
    private Button botonintent;
    private TextView resultado;

    //declaramos el ActivityResultLauncher

    private final ActivityResultLauncher<Intent> activityResultLauncher=
            //el resgister registra el resultlauncher, tomando dos parametros un contracts y un callback
            //" result ->" es para indicar como se recibe un resultado
            //Este es un lambda que actúa como una implementación de la interfaz ActivityResultCallback<ActivityResult>.
            // es una funcion que se ejecuta al recibir un resultado de la actividad que se lanzo
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode()==RESULT_OK){
                    //comprueba si el codigo de resultado obtenido es igual al codigo de result ok
                    Intent data=result.getData();

                    //.getdata devuelve un intent con los datos de la actividad finalizada
                    //aqui estan los apellidos

                    if(data !=null){
                        String apellido=data.getStringExtra("APELLIDO");
                        String nome= nombre.getText().toString();
                        resultado.setText("Resultado, nombre: "+nome+" apellido: "+apellido);
                    }
                }

                    } );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio8);

         nombre=findViewById(R.id.editTextText);
         destruir=findViewById(R.id.checkBox3);
        botonintent=findViewById(R.id.botonintent);
        resultado=findViewById(R.id.resultado);

        botonintent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String name = nombre.getText().toString();

                Intent intent=new Intent(Ejercicio9.this, Ejercicio9_2.class);

                //el primer parametro es la clave y el segundo es el valor
                intent.putExtra("NOMBRE", name);

                //comentado para poder usar ActivityResult
                //startActivity(intent);

                activityResultLauncher.launch(intent);


                //chehkbox
                if(destruir.isChecked()){
                    finish();
                }


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });





    }


}