package com.hectorgc.ejemploslayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;

public class Ejercicio9 extends AppCompatActivity {


    private EditText nombre;
    private CheckBox destruir;
    private Button irOtraActividad;
    private TextView resultado;
    private Button irTelefono;
    private Button irNavegador;
    private Button irLocalizacion;

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
        setContentView(R.layout.activity_ejercicio9);

         nombre=findViewById(R.id.editTextText);
         destruir=findViewById(R.id.checkBox3);
        irOtraActividad=findViewById(R.id.irOtraActividad);
        resultado=findViewById(R.id.resultado);
        irTelefono=findViewById(R.id.irTelefono);
        irLocalizacion=findViewById(R.id.irLocalizacion);
        irNavegador=findViewById(R.id.irNavegador);

        irOtraActividad.setOnClickListener(new View.OnClickListener(){

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
//iniciar activity telefono

        // poner v -> substituye a : new View.OnClickListener()
        irTelefono.setOnClickListener(v ->{

            Intent intent= new Intent(Ejercicio9.this, E9_telefono.class);
            startActivity(intent);

        });
//iniciar activity navegador
        irNavegador.setOnClickListener(v->{
            Intent intent=new Intent(Ejercicio9.this, E9_Navegador.class);
            startActivity(intent);
        });


        //no funciona

        irLocalizacion.setOnClickListener(v->{
            //Uri significa identificador uniforme de recursos
            //hay que parsear el texto para que sea Uri y google maps lo entienda
            //geo:0,0 es un formato genérico que se puede usar cuando no tienes coordenadas exactas
            // y el parámetro q=Plaza+Mayor,+Madrid es para que busque ese sitio por defecto

            //Uri gmmIntentUri=Uri.parse("geo:0,0?q=Plaza+Mayor,+Madrid");
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=Plaza+Mayor+Madrid");

            Intent mapIntent=new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            //esto sirve para que el intent use especificamente la app de google maps
            //mapIntent.setPackage("com.google.android.apps.maps");

            //comprueba si el intent puede resolverse, osea verificia que alguna app pueda abririse con el intent
            if(mapIntent.resolveActivity(getPackageManager())!=null){
                startActivity(mapIntent);
            }else{
                Toast.makeText(Ejercicio9.this, "google maps no esta disponible", Toast.LENGTH_SHORT).show();
            }

        });









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });





    }


}