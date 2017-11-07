package com.example.missj.goaduch.com.example.missj.sqlitedal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.database.SQLiteDALBase;
import com.example.missj.goaduch.com.example.missj.model.ModelUser;
import com.example.missj.goaduch.com.example.missj.utility.*;

import java.util.Date;
import java.util.List;

/**
 * Created by miss.j on 2017/10/29.
 */

public class SQLiteDALUser  extends SQLiteDALBase {
    public  SQLiteDALUser(Context pContext)
    {
        super(pContext);
    }

    @Override
    protected String[] getTableNameAndPK() {

            return new String[]{"User","UserID"};


    }

    public  boolean insertUser(ModelUser pUser)
{
    ContentValues values = createParms(pUser);
    long _id = getSQLiteDatabase().insert(getTableNameAndPK()[0], null,values);
    pUser.setUserId((int)_id);
    return  _id>0;
}
public  boolean DeleteUser(String pCondition)
{
    return  Delete(getTableNameAndPK()[0], pCondition);
}
public  boolean UpdateUser(String pConditionn,ModelUser pModelUser)
{
    ContentValues _ContentValues = createParms(pModelUser);
    return  getSQLiteDatabase().update(getTableNameAndPK()[0], _ContentValues, pConditionn,null)> 0;
}
    public  boolean UpdateUser(String pConditionn,ContentValues pContentValues)
    {
      //  ContentValues _ContentValues = createParms(pModelUser);
        return  getSQLiteDatabase().update(getTableNameAndPK()[0], pContentValues, pConditionn,null)> 0;
    }
    public List<ModelUser> getUser(String pCondition)
    {
        String _SqlText = "Select * From User Where 1=1 "+pCondition;
        return  GetList(_SqlText);
    }
    public List<ModelUser> getUser(Cursor pCursor)
    {

        return  CursorToList(pCursor);
    }

    protected  Object FindModel(Cursor pCursor)
    {
        ModelUser  _ModelUser = new ModelUser();
        _ModelUser.setUserId(pCursor.getInt(pCursor.getColumnIndex("UserID")));
        _ModelUser.setUserName(pCursor.getString(pCursor.getColumnIndex("UserName")));

        Date _CreateDate = DateTools.getDate(pCursor.getString(pCursor.getColumnIndex("CreateDate")),"yyyy-MM-dd HH:mm:ss");
        _ModelUser.setCreateDate(_CreateDate);
        _ModelUser.setState(pCursor.getInt(pCursor.getColumnIndex("State")));
        return _ModelUser;
    }
   // public  boolean
    @Override
    public void OnCreate(SQLiteDatabase pSQLiteDatabase) {
        StringBuilder _StringBuilder = new StringBuilder();
        _StringBuilder.append("create table User(");
        _StringBuilder.append("[UserID] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
        _StringBuilder.append(",[UserName] varchar(10) NOT NULL");
        _StringBuilder.append(",[CreateDate] datetime NOT NULL");
        _StringBuilder.append(",[State] integer NOT NULL");
        _StringBuilder.append(")");

        pSQLiteDatabase.execSQL(_StringBuilder.toString());
        initDefaultData(pSQLiteDatabase);

    }

    @Override
    public void OnUpGrade(SQLiteDatabase pSQLiteDatabase) {

    }
    public ContentValues createParms(ModelUser pInfo)
    {
        ContentValues _ContenValuse = new ContentValues();
        _ContenValuse.put("UserName", pInfo.getUserName());

        _ContenValuse.put("CreateDate",DateTools.getFormatDateTime(pInfo.getCreateDate(),  "yyyy-MM-dd HH:mm:ss"));
        _ContenValuse.put("State", pInfo.getState());
        return  _ContenValuse;
    }
    private  void initDefaultData(SQLiteDatabase pSQLiteDataBase)
    {

        ModelUser _user=new ModelUser();
        String[] _datas= getContext().getResources().getStringArray(R.array.InitDefaultUserName);
        for (int i = 0; i < _datas.length; i++) {
            String name = _datas[i];
            _user.setUserName(name);

            ContentValues values=createParms(_user);
            pSQLiteDataBase.insert(getTableNameAndPK()[0], null, values);
        }
    }
}
