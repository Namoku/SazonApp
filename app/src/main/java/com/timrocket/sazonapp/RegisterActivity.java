package com.timrocket.sazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText et1, et2;
    Button registrar;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.edpassword,".{6,}",R.string.invalid_pass);
        et1 = (EditText) findViewById(R.id.edemail);
        et2 = (EditText) findViewById(R.id.edpassword);
        registrar = (Button) findViewById(R.id.btncrear);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                if(awesomeValidation.validate()){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Usuario creado con exito",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(RegisterActivity.this,"Error al agregar usuario",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(RegisterActivity.this,"Completa todos los campos",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}