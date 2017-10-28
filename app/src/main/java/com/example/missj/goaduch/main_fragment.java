package com.example.missj.goaduch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.missj.goaduch.com.example.missj.Adapter.AdaperAppGrid;

/**
 * Created by miss.j on 2017/10/28.
 */

public class main_fragment extends Fragment {
    View v;
    private GridView gvAppGrid;
    private AdaperAppGrid mAdaperAppGrid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v= inflater.inflate(R.layout.fragment_main, container,false);
       AppendMainBody(R.layout.main_body);

       InitVarible();
       InitView();
       InitListeners();
       BindData();

       return  v;

    }
protected  void AppendMainBody(int pResId)
{
    LinearLayout _MainBody = (LinearLayout)v.findViewById(R.id.layMainBody);
    View _View = LayoutInflater.from(getActivity()).inflate(pResId, null);
    RelativeLayout.LayoutParams _LayoutParams  = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
    _MainBody.addView(_View,_LayoutParams);
}
public  void InitView()
{
    gvAppGrid = (GridView)v.findViewById(R.id.gvAppGird);

}
public  void InitVarible()
{
    mAdaperAppGrid = new AdaperAppGrid(getActivity());

}
public  void BindData()
{
    gvAppGrid.setAdapter(mAdaperAppGrid);
}
public  void InitListeners()
{

}

}
