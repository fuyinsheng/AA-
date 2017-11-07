package com.example.missj.goaduch.com.example.missj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.Adapter.AdaperAppGrid;

/**
 * Created by miss.j on 2017/10/28.
 */

public class main_fragment extends FrameBase {
    View v;
    private GridView gvAppGrid;
    private AdaperAppGrid mAdaperAppGrid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v= inflater.inflate(R.layout.fragment_main, container,false);
       AppendFragment(MAINBODY);

/*       InitVarible();
       InitView();
       InitListeners();
       BindData();*/

       return  v;

    }

public  void InitView()
{
    gvAppGrid = (GridView)v.findViewById(R.id.gvAppGird);

}
public  void InitVarible()
{
    mAdaperAppGrid = new AdaperAppGrid(getContext());

}
public  void BindData()
{
    gvAppGrid.setAdapter(mAdaperAppGrid);
}
public  void InitListeners()
{

}

}
