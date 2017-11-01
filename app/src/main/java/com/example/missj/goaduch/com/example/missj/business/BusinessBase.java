package com.example.missj.goaduch.com.example.missj.business;

import android.content.Context;

/**
 * Created by miss.j on 2017/10/30.
 */

public class BusinessBase {
    private Context mContext;

    public BusinessBase(Context context) {
        mContext = context;
    }


     protected String   getString(int pResId)
     {
         return  mContext.getString(pResId);
     }
    protected String   getString(int pResId, Object[] pObject)
    {
        return  mContext.getString(pResId, pObject);
    }
}
