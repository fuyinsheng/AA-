package com.example.missj.goaduch.com.example.missj.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.missj.goaduch.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by miss.j on 2017/10/29.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private  static  SQLiteDataBaseConfig SQLITEDATABASE_CONFIG;
    private  Context mContext;
    private  static SQLiteOpenHelper INSTANCE;
    private com.example.missj.goaduch.com.example.missj.database.Reflection  mReflection;
    /*定义了接口*/
    public  interface SQLiteDataTable
    {
        public abstract  void OnCreate(SQLiteDatabase pSQLiteDatabase);
        public abstract  void OnUpGrade(SQLiteDatabase pSQLiteDatabase);


    }




    private SQLiteHelper(Context context  ) {
        super(context, SQLITEDATABASE_CONFIG.GetDataBaseName(), null, SQLITEDATABASE_CONFIG.getVersion(), null);
        mContext =context;
    }
    public  static  SQLiteOpenHelper GetInstance(Context pContext)
    {
        if(INSTANCE == null)
        {
            SQLITEDATABASE_CONFIG = SQLITEDATABASE_CONFIG.getInstance(pContext);
            INSTANCE = new SQLiteHelper(pContext);
        }
        return  INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ArrayList<String> _ArrayList  = SQLITEDATABASE_CONFIG.getTables();
        mReflection = new com.example.missj.goaduch.com.example.missj.database.Reflection();
        for (int i = 0 ; i < _ArrayList.size();i++)
        {
            try {
                SQLiteDataTable _SQLiteDataTable = (SQLiteDataTable) mReflection.newInstance(_ArrayList.get(i),
                   new Object[] {mContext},
                        new  Class[] {Context.class});
                _SQLiteDataTable.OnCreate(sqLiteDatabase);
            } catch (Exception e) {
                e.printStackTrace();

        }


        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
