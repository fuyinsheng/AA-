package com.example.missj.goaduch.com.example.missj.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;

import java.lang.reflect.Field;

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
    protected void  ShowUserAddOrEditDialog( int pResId)
    {
            mSliderMenuView.Toggle();
    }
    protected LayoutInflater getLayouInflater()
    {
        LayoutInflater _LayoutInflater =LayoutInflater.from(getActivity());
        return  _LayoutInflater;

    }
    /*
    ***通过反射的方式去访问类的内部变量，并且修改
     */
    public  void  SetAlerDialogIsClose(DialogInterface pDialog , Boolean pIsClosed)
    {
        Field _Field = null;
        try {
            /*通过getClass获取对象的类名，然后获取超类的类名，再获取超类中声明的mShowing字段*/
            _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            _Field.setAccessible(true);     //设置字段可以访问,
            _Field.set(pDialog, pIsClosed); //修改对象的字段的值
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    protected AlertDialog ShowAlertDialog(int pTitleResId, String pMessage, DialogInterface.OnClickListener pDialogInterface)
    {
        String _Title = getResources().getString(pTitleResId);
        return  ShowAlertDialog(_Title, pMessage, pDialogInterface);
    }
   protected  AlertDialog ShowAlertDialog(String pTitle, String pMessage, DialogInterface.OnClickListener pDialogInterface )
    {
        return  new AlertDialog.Builder(getContext())
                            .setTitle(pTitle)
                                .setMessage(pMessage)
                            .setPositiveButton(R.string.ButtonTextYes, (DialogInterface.OnClickListener)pDialogInterface)
                            .setNegativeButton(R.string.ButtonTextNo, null)
                        .show();
    }
    protected  void createMenu(Menu pMenu)
    {
        int _Item[] = {1,2};
        int _GroupID = 0;
        int _Order = 0;
        for (int i: _Item)
        {
            switch (i)
            {
                case 1:
                    pMenu.add(_GroupID, i ,_Order, R.string.MenuTextEdit);

                    break;
                case 2:
                    pMenu.add(_GroupID, i ,_Order, R.string.MenuTextDelete);
                    break;
                    default:
                        break;
            }
        }
    }
}
