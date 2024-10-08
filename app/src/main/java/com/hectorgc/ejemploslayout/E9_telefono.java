package com.hectorgc.ejemploslayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import android.widget.Toast;


public class E9_telefono extends AppCompatActivity {


    private EditText TextoTelefono;
    private ImageButton Llamar;
    private Button CerrarTelf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_e9_telefono);

        TextoTelefono=findViewById(R.id.TextoTelefono);
        Llamar=findViewById(R.id.Llamar);
        CerrarTelf=findViewById(R.id.CerrarTelf);

        Llamar.setOnClickListener(v-> {
            String numero=TextoTelefono.getText().toString();
            if(!numero.isEmpty()) {
                //Verifica si el permiso de CALL_PHONE ha sido concedido en el manifest.xml.
                // Devuelve PackageManager.PERMISSION_GRANTED si se ha concedido.
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {

                    //Si el permiso ya fue concedido
                    //crea un intent para realizar la llamada
                    //se le pasa con Uri parse el numero de telefono
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + numero));
                    startActivity(intent);
                }
            }else{
                Toast.makeText(E9_telefono.this, "Por favor introduce un numero de telefono", Toast.LENGTH_SHORT).show();
            }

        });
        // Cerrar la actividad
        CerrarTelf.setOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}