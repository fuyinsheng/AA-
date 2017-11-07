package com.example.missj.goaduch.com.example.missj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.Adapter.AdaperAppGrid;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;

/**
 * Created by miss.j on 2017/10/28.
 */

public class MainBodyFragment extends FrameBase implements SliderMenuView.OnSlideMenuListenner {
    View v;
    private GridView gvAppGrid;
    private AdaperAppGrid mAdaperAppGrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_body, container, false);

        InitVarible();
       InitView();
       InitListeners();
       BindData();
       CreateSlideMenu(R.array.SlideMenuActivityMain);
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
            gvAppGrid.setOnItemClickListener( new onGridItemClickListener());

    }
private  class  onGridItemClickListener implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String _MenuName = (String)adapterView.getAdapter().getItem(i);

            if(_MenuName.equals(getString(R.string.appGridTextUserManage)))
            {
                AppendFragment(USERFRAG);
            }
        }
    }
    @Override
    public void onSlidMenuItemClick(View pView, SliderMenuItem pSlideMenuItemClick) {
        Toast.makeText(getActivity(), pSlideMenuItemClick.getTitle(),Toast.LENGTH_SHORT).show();

    }
}
