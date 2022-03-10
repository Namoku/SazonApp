package com.timrocket.sazonapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupTabFragment extends Fragment {
    EditText nombre, email, contraseña, confcontraseña;
    Button signup;
    float v = 0;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(getActivity(),R.id.nombre,".{4,}",R.string.invalid_name);
        awesomeValidation.addValidation(getActivity(),R.id.etemail1, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(getActivity(),R.id.etpassword1,".{6,}",R.string.invalid_pass);
        awesomeValidation.addValidation(getActivity(), R.id.password2, R.id.etpassword, R.string.err_password_confirmation);
        email = root.findViewById(R.id.etemail1);
        contraseña = root.findViewById(R.id.etpassword1);
        confcontraseña = root.findViewById(R.id.password2);
        nombre = root.findViewById(R.id.nombre);
        signup = root.findViewById(R.id.Btn_signup);

        email.setTranslationX(0);
        contraseña.setTranslationX(0);
        confcontraseña.setTranslationX(0);
        nombre.setTranslationX(0);
        signup.setTranslationX(0);

        email.setAlpha(v);
        contraseña.setAlpha(v);
        confcontraseña.setAlpha(v);
        nombre.setAlpha(v);
        signup.setAlpha(v);

        nombre.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        contraseña.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confcontraseña.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = contraseña.getText().toString();
                String pass2 = confcontraseña.getText().toString();
                if(awesomeValidation.validate()){
                        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getActivity(),"Usuario creado con exito",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getActivity(),"Error al agregar usuario",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                }else{
                    System.out.println(awesomeValidation.validate());
                    Toast.makeText(getActivity(),"Completa todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });



        return root;
    }
}