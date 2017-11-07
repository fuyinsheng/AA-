package com.example.missj.goaduch.com.example.missj.business;

import android.content.ContentValues;
import android.content.Context;

import com.example.missj.goaduch.com.example.missj.model.ModelUser;
import com.example.missj.goaduch.com.example.missj.sqlitedal.SQLiteDALUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miss.j on 2017/10/30.
 */

public class BusinessUser extends BusinessBase {
    private SQLiteDALUser mSQLiteDALUser;
    public BusinessUser(Context context) {
        super(context);
        mSQLiteDALUser = new SQLiteDALUser(context);
    }
    public  boolean insertUser(ModelUser pModelUser)
    {
        boolean _Result = mSQLiteDALUser.insertUser(pModelUser);
        return  _Result;
    }
    public  boolean deleteUser(int pUserId)
    {
        String _Condition = "And UserID = "+pUserId;
        boolean _Result = mSQLiteDALUser.DeleteUser(_Condition);
        return  _Result;
    }
    public  boolean updateUserByUserID(ModelUser pModelUser)
    {
            String _Condition = " UserID = " +pModelUser.getUserId();
            boolean _Result = mSQLiteDALUser.UpdateUser(_Condition, pModelUser);
            return  _Result;

    }
    public List<ModelUser> GetUser(String pCondition)
    {
        return  mSQLiteDALUser.getUser(pCondition);
    }
    public  ModelUser getModelUserByUserId(int pID)
    {
        List<ModelUser> _List = mSQLiteDALUser.getUser(" And UserID = "+pID);
        if(_List.size() == 1)
        {
            return _List.get(0);
        }else {
            return null;
        }
    }
    public  List<ModelUser> getUserListByUserID(String[] pID )
    {
        List<ModelUser> _List = new ArrayList<>();
    for ( int i= 0 ; i < pID.length; i++)
    {
        _List.add(getModelUserByUserId(Integer.valueOf(pID[i])));
    }
    return  _List;
    }

    public  boolean IsExistUserByUserName(String pUserName, Integer pUserId)
    {
        String _Condition = "And UserName = '" +pUserName + "'";
        if (pUserId != null)
        {
            _Condition +="And UserId <>" +pUserId; //表示除了这个pUserId
        }
        List _List = mSQLiteDALUser.getUser(_Condition);
        if(_List.size()> 0)
            return  true;
        else
            return  false;
    }
    public  Boolean HideUserByUserID(int pUserID)
    {
        String _Condition = "UserID =" +pUserID ;
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("State", 0 );

        Boolean _Result = mSQLiteDALUser.UpdateUser(_Condition, _ContentValues);
        if(_Result)
            return  true;
        else
            return  false;
    }




    public  List<ModelUser> getNotHideUser()
    {
        return  mSQLiteDALUser.getUser("And State = 1");
    }
}
