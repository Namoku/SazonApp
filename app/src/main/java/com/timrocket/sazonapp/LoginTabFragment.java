package com.timrocket.sazonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginTabFragment extends Fragment {
    EditText email, contraseña;
    Button login;
    TextView forgetPass;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        contraseña = root.findViewById(R.id.password);
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




        return root;
    }
}