package com.example.missj.goaduch.com.example.missj.control;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.missj.goaduch.com.example.missj.fragment.FrameBase;
import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.Adapter.AdapterSlideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miss.j on 2017/10/28.
 */

public class SliderMenuView {
    private Activity mActivity;
    private List mMenuList;
    private Boolean mIsClosed; //是否关闭
    private RelativeLayout layBottomBox;

    private  OnSlideMenuListenner mOnSlideMenuListenner;
    public interface  OnSlideMenuListenner
    {
            public abstract  void onSlidMenuItemClick(View pView, SliderMenuItem pSlideMenuItemClick);
    }

    private  void open()
    {
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                            RelativeLayout.LayoutParams.MATCH_PARENT);

        _LayoutParams.addRule(RelativeLayout.BELOW, R.id.IncludeTitle);
        layBottomBox.setLayoutParams(_LayoutParams);
        mIsClosed = false;
    }
    private  void  close()
    {
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                200);

        _LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layBottomBox.setLayoutParams(_LayoutParams);
        mIsClosed = true;
    }
    public   void Toggle()
    {
        if(mIsClosed)
            open();
        else
            close();

    }
    public void Add(SliderMenuItem pSliderMenuItem )
    {
        mMenuList.add(pSliderMenuItem );
    }
    public void BindList()
    {
        AdapterSlideMenu _AdapterSlideMenu = new AdapterSlideMenu(mActivity, mMenuList);
        ListView _ListView = mActivity.findViewById(R.id.lvSlideList);
        _ListView.setAdapter(_AdapterSlideMenu);
        _ListView.setOnItemClickListener(new onSlideMenuItemClickListener());
    }
    private void OnSlideMenuView()
    {

    }
    public  void  InitVarible()
    {
        mMenuList = new ArrayList();
        mIsClosed = true;
    }
    public  void  InitView()
    {
        layBottomBox = (RelativeLayout)mActivity.findViewById(R.id.IncludeBottom);
    }
    public  void InitListeners()
    {
        layBottomBox.setOnClickListener(new onSlideMenuClick());
    }
    private  class  onSlideMenuClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Toggle();}
    }
    private  class  onSlideMenuItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            SliderMenuItem _SliderMenuItem = (SliderMenuItem)adapterView.getItemAtPosition(position);
          mOnSlideMenuListenner.onSlidMenuItemClick(view, _SliderMenuItem);
        }
    }
    public SliderMenuView(Activity pActivity, FrameBase mFrameBase) {
    mActivity = pActivity;
    mOnSlideMenuListenner = (OnSlideMenuListenner)mFrameBase;
    InitVarible();
    InitView();
    InitListeners();

    }
}
