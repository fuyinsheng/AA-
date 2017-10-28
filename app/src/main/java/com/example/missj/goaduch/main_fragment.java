package com.example.missj.goaduch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by miss.j on 2017/10/28.
 */

public class main_fragment extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v= inflater.inflate(R.layout.fragment_main, container,false);
       return  v;

    }
}
