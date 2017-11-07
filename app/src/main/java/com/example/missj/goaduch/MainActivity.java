package com.example.missj.goaduch;

import android.support.v4.app.Fragment;

import com.example.missj.goaduch.com.example.missj.fragment.SingleFragmentActivity;
import com.example.missj.goaduch.com.example.missj.fragment.main_fragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new main_fragment();
    }
}
