package com.timrocket.sazonapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {
    EditText email, contraseña;
    Button login;
    TextView forgetPass;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

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
        awesomeValidation.addValidation(getActivity(),R.id.etemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //hacemos validacion para que la contraseña tenga mas de 6 caracteres
        awesomeValidation.addValidation(getActivity(),R.id.etpassword,".{6,}",R.string.invalid_pass);

        email = root.findViewById(R.id.etemail);
        contraseña = root.findViewById(R.id.etpassword);
        login = root.findViewById(R.id.Btn_login);
        forgetPass = root.findViewById(R.id.forgetpass);

        email.setTranslationX(0);
        contraseña.setTranslationX(0);
        login.setTranslationX(0);
        forgetPass.setTranslationX(0);

        email.setAlpha(v);
        contraseña.setAlpha(v);
        login.setAlpha(v);
        forgetPass.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        contraseña.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String mail = email.getText().toString();
                    String password = contraseña.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                goHome();
                            }else{
                                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });



        return root;
    }

    private void goHome(){
        Intent a = new Intent(getActivity(),HomeActivity.class);
        a.putExtra("mail",email.getText().toString());
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        getActivity().finish();
    }

}