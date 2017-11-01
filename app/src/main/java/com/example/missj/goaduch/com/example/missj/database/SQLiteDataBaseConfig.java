package com.example.missj.goaduch.com.example.missj.database;

import android.content.Context;

import com.example.missj.goaduch.R;

import java.util.ArrayList;

/**
 * Created by miss.j on 2017/10/29.
 */
/*封装数据库基本操作*/
public class SQLiteDataBaseConfig {
    private static final String DATABASE_NAME = "GoDutchDataBase";
    private static final int VERSION = 1;
    private static SQLiteDataBaseConfig mSQLitDataBaseConfig;
    private  static Context mContext;
    private SQLiteDataBaseConfig() {
    }

    /*创建一个单例模式*/
    public static SQLiteDataBaseConfig getInstance(Context pContext) {
        mContext = pContext;
        if (mSQLitDataBaseConfig == null) {

            mSQLitDataBaseConfig = new SQLiteDataBaseConfig();
        }

        return mSQLitDataBaseConfig;
    }

    public int getVersion() {
        return VERSION;
    }

    public String GetDataBaseName()
    {
        return  DATABASE_NAME;
    }

    public  ArrayList<String> getTables()
    {
        ArrayList<String> _ArrayList = new ArrayList<>();

        String[] _SQLiteDALClassName = mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
        String _PackagePath = mContext.getPackageName()+ ".com.example.missj.sqlitedal.";
        for(int i = 0 ; i < _SQLiteDALClassName.length; i++)
        {
        _ArrayList.add(_PackagePath +_SQLiteDALClassName[i]);
        }
        return  _ArrayList;
    }
}
