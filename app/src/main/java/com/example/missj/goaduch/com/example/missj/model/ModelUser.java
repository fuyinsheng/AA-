package com.example.missj.goaduch.com.example.missj.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.missj.goaduch.com.example.missj.database.SQLiteDALBase;

import java.util.Date;

/**
 * Created by miss.j on 2017/10/29.
 */

public class ModelUser {
    /**
     * 用户表主键ID
     */
    private int mUserId;
    /**
     * 用户名称
     */
    private String mUserName;
    /**
     * 添加日期
     */
    private Date mCreateDate = new Date();
    /**
     * 状态0失效，1启用
     */
    private int mState = 1;

    public int getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public Date getCreateDate() {
        return mCreateDate;
    }

    public int getState() {
        return mState;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setCreateDate(Date createDate) {
        mCreateDate = createDate;
    }

    public void setState(int state) {
        mState = state;
    }


}
