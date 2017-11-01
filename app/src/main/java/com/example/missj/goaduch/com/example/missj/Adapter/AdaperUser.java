package com.example.missj.goaduch.com.example.missj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.business.BusinessUser;
import com.example.missj.goaduch.com.example.missj.model.ModelUser;

import java.util.List;

/**
 * Created by miss.j on 2017/10/28.
 */

public class AdaperUser extends AdapterBase {

    private class Holder
    {
        ImageView ivUserIcon;
        TextView tvUserName;


    }


    public AdaperUser(Context pContext)
    {
        super(pContext, null);
        BusinessUser  _BusinessUser = new BusinessUser(pContext);
        List _List = _BusinessUser.getNotHideUser();
        setList(_List);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder _Holder;
            if(view == null)
            {
               view  = GetInflater().inflate(R.layout.fragment_user_item,null);

                _Holder  = new Holder();
                _Holder.tvUserName = (TextView) view.findViewById(R.id.tvUserName);
                _Holder.ivUserIcon = (ImageView) view.findViewById(R.id.ivUserIcon);
                view.setTag(_Holder);
            }
            else
            {
                _Holder = (Holder)view.getTag();

            }
        ModelUser _Info = (ModelUser) getList().get(i);

        _Holder.ivUserIcon.setImageResource(R.mipmap.user_big_icon);
        _Holder.tvUserName.setText(_Info.getUserName());

        return view;
    }
}
