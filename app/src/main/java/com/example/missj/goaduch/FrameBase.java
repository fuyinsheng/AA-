package com.example.missj.goaduch;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;

import java.lang.ref.SoftReference;

/**
 * Created by miss.j on 2017/10/28.
 */

public class FrameBase extends Fragment {
   private SliderMenuView mSliderMenuView;
    protected  void AppendMainBody(int pResId)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment  mFragment =  fm.findFragmentById(R.id.layMainBody);
        if(mFragment == null)
        {
            mFragment =new MainBodyFragment();
            fm.beginTransaction().add(R.id.layMainBody, mFragment)
                    .commit();


        }
    }

    protected void  CreateSlideMenu( int pResId)
    {
       mSliderMenuView = new SliderMenuView(getActivity(), this);
        String[] _MenuItenArray = getActivity().getResources().getStringArray(pResId);
        for(int i = 0 ; i < _MenuItenArray.length;i++)
        {
            SliderMenuItem _SlideMenuItem = new SliderMenuItem(i, _MenuItenArray[i]);
            mSliderMenuView.Add(_SlideMenuItem);
            mSliderMenuView.BindList();

        }
    }
}
