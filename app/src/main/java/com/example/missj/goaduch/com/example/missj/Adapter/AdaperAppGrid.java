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

/**
 * Created by miss.j on 2017/10/28.
 */

public class AdaperAppGrid extends BaseAdapter {

    private class Holder
    {
        ImageView ivIcon;
        TextView tvName;


    }
    private  Integer[] m_ImageInteger ={
            R.mipmap.grid_payout,
            R.mipmap.grid_bill,
            R.mipmap.grid_report,
            R.mipmap.grid_account_book,
            R.mipmap.grid_category,
            R.mipmap.grid_user,
    };
    private  String[] mImagString = new String[6];
    private  Context mContext ;
    public  AdaperAppGrid(Context pContext)
    {
        mContext = pContext;
        mImagString[0] = pContext.getString(R.string.appGridTextPayoutAdd);
        mImagString[1] = pContext.getString(R.string.appGridTextPayoutManager);
        mImagString[2] = pContext.getString(R.string.appGridTextStatisticsManager);
        mImagString[3] = pContext.getString(R.string.appGridTextAccountBookManager);
        mImagString[4] = pContext.getString(R.string.appGridTextCategoryManager);
        mImagString[5] = pContext.getString(R.string.appGridTextUserManage);

    }
    @Override
    public int getCount() {
        return mImagString.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
         return  mImagString[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder _Holder;
            if(view == null)
            {
                LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
                view = _LayoutInflater.inflate(R.layout.main_body_item,null);
                _Holder  = new Holder();
                _Holder.ivIcon = (ImageView)view.findViewById(R.id.ivIcon);
                _Holder.tvName = (TextView)view.findViewById(R.id.tvName);
                view.setTag(_Holder);
            }
            else
            {
                _Holder = (Holder)view.getTag();

            }
            _Holder.ivIcon.setImageResource(m_ImageInteger[i]);
        LinearLayout.LayoutParams _layoutParams = new LinearLayout.LayoutParams(200,
                                200);
        _Holder.ivIcon.setLayoutParams(_layoutParams);
        _Holder.ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);
        _Holder.tvName.setText(mImagString[i]);
        return view;
    }
}
