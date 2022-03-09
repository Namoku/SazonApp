package com.timrocket.sazonapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Login", "Signup"};

    public LoginAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment = new SignupTabFragment();
                return signupTabFragment;
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}