package com.example.missj.goaduch.com.example.missj.control;

/**
 * Created by miss.j on 2017/10/28.
 */

public class SliderMenuItem {
    private  int mItemID;
    private String mTitle;

    public SliderMenuItem(int itemID, String title) {
        mItemID = itemID;
        mTitle = title;
    }

    public int getItemID() {
        return mItemID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setItemID(int itemID) {
        mItemID = itemID;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
