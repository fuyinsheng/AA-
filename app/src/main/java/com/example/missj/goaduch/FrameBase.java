package com.example.missj.goaduch;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Switch;

import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;

import java.lang.ref.SoftReference;

/**
 * Created by miss.j on 2017/10/28.
 */

public class FrameBase extends Fragment {
   private SliderMenuView mSliderMenuView;
    public   final  int MAINBODY = 0;
    public   final  int USERFRAG = 1;
    public static int CurrentFrag ;
    private Fragment FragmentFactory( int pFrag)
   {
       switch(pFrag )
       {
           case MAINBODY:
               return  new MainBodyFragment();
           case USERFRAG:
               return  new UserBodyFragment();
               default:
                   return null;
       }
   }
    protected  void AppendFragment(int pFrag)
    {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment  mFragment =  fm.findFragmentById(R.id.layMainBody); //R.id.layMainBody
        if(mFragment == null)
        {
            mFragment =FragmentFactory(pFrag);
            fm.beginTransaction().add(R.id.layMainBody, mFragment)  //R.id.layMainBody
                    .commit();

            CurrentFrag = MAINBODY;
        }
          if(pFrag  != CurrentFrag)
        {
            mFragment =FragmentFactory(pFrag);
            fm.beginTransaction().replace(R.id.layMainBody, mFragment)  //R.id.layMainBody
                    .commit();

            CurrentFrag = pFrag;

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
