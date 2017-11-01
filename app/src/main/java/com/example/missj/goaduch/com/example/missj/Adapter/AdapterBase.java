package com.example.missj.goaduch.com.example.missj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;



/**
 * Created by miss.j on 2017/10/28.
 */

public abstract  class AdapterBase extends BaseAdapter {
    private List mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public AdapterBase(Context pContext, List pList)
    {
        mContext = pContext;
        mList = pList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public List getList() {
        return mList;
    }
    public void setList(List pList) {
         mList = pList;
    }

    public LayoutInflater GetInflater()
    {	return mLayoutInflater;
    }
    public Context GetContext()
    {	return mContext;
    }
    public int getCount()
    {
        return mList.size();
    }

    public Object getItem(int pPosition)
    {
        return mList.get(pPosition);
    }

    public long getItemId(int pPosition)
    {
        return pPosition;
    }

}
