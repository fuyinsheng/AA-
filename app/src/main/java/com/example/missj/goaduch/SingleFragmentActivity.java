package com.example.missj.goaduch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by miss.j on 2017/10/28.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment createFragment();

    protected  void showMessage(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    protected  void startActivity(Class<?> pClass)
    {
        Intent _Intent = new Intent();
        _Intent.setClass(this ,pClass);
        startActivity(_Intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment  mFragment =  fm.findFragmentById(R.id.fragment_container);
        if(mFragment == null)
        {
            mFragment =createFragment();
            fm.beginTransaction().add(R.id.fragment_container, mFragment)
                    .commit();


        }
    }



}
