package com.example.missj.goaduch.com.example.missj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;

import java.util.List;

/**
 * Created by miss.j on 2017/10/28.
 */

public class AdapterSlideMenu extends AdapterBase {
    public AdapterSlideMenu(Context pContext, List pList) {
        super(pContext, pList);
    }
    private  class  Holder
    {
        TextView tvMenuName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder _Holder;
        if(view == null)
         {
         view =  GetInflater().inflate(R.layout.slidemenu_list_item, null);

             _Holder = new Holder();

             _Holder.tvMenuName = (TextView)view.findViewById(R.id.tvMenuName);
             view.setTag(_Holder);
         }
         else {
            _Holder = (Holder)view.getTag();

        }
        SliderMenuItem  _Item = (SliderMenuItem) getList().get(i);
        _Holder.tvMenuName.setText(_Item.getTitle());
        return  view;
    }
}
