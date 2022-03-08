package com.timrocket.sazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email, pass;
    Button btnlogin, btnregister;
    Intent a;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Obtenemos la isntancia de la base de datos para autentificar los usuarios
        firebaseAuth = FirebaseAuth.getInstance();
        //Verificamos si ya hay una sesion iniciada
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //if(user != null){
          //gohome();
        //}

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //hacemos validacion de que se ingrese un formato de correo aceptado
        awesomeValidation.addValidation(this,R.id.editTextTextEmailAddress, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //hacemos validacion para que la contraseña tenga mas de 6 caracteres
        awesomeValidation.addValidation(this,R.id.editTextTextPassword,".{6,}",R.string.invalid_pass);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnregister = (Button) findViewById(R.id.btnregister);
        //Boton login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String mail = email.getText().toString();
                    String password = pass.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Verificacion de login
                            if(task.isSuccessful()){
                                gohome();
                            }else{
                                Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(a);
                finish();
            }
        });
    }
    private void gohome(){
        Intent a = new Intent(LoginActivity.this,HomeActivity.class);
        a.putExtra("mail",email.getText().toString());
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}

