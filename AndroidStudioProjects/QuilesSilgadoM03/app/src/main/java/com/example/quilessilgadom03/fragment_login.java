package com.example.quilessilgadom03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class fragment_login extends Activity {
    private TextInputEditText usuario;
    private TextInputEditText contrasena;

    private TextView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        usuario = findViewById(R.id.texto_Nombre);
        contrasena = findViewById(R.id.texto_Password);
        respuesta = findViewById(R.id.texto_correcto);


    }
    public void login(View view){
        String user = usuario.getText().toString();
        String password = contrasena.getText().toString();
        if(user.equals("admin") && password.equals("admin")){

            respuesta.setText("Usuario y contraseña correcta");
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("usuario",user);
            startActivity(intent);
        }else {
            respuesta.setText("Usuario o contraseña incorrecta");
        }
    }
}
