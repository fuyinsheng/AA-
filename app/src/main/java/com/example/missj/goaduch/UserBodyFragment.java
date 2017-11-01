package com.example.missj.goaduch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.missj.goaduch.com.example.missj.Adapter.AdaperAppGrid;
import com.example.missj.goaduch.com.example.missj.Adapter.AdaperUser;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;

/**
 * Created by miss.j on 2017/10/28.
 */

public class UserBodyFragment extends FrameBase implements SliderMenuView.OnSlideMenuListenner {
    View v;
    private ListView  mUserList;
    private AdaperUser mAdaperUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user, container, false);

        InitVarible();
       InitView();
       InitListeners();
       BindData();
       CreateSlideMenu(R.array.SlideMenuActivityMain);
        return  v;
    }

    public  void InitView()
    {
        mUserList = (ListView) v.findViewById(R.id.lvUserList);

    }
    public  void InitVarible()
    {
        mAdaperUser = new AdaperUser(getContext());

    }
    public  void BindData()
    {
        mUserList.setAdapter(mAdaperUser);
    }
    public  void InitListeners()
    {

    }

    @Override
    public void onSlidMenuItemClick(View pView, SliderMenuItem pSlideMenuItemClick) {
        Toast.makeText(getActivity(), pSlideMenuItemClick.getTitle(),Toast.LENGTH_SHORT).show();
    }
}
