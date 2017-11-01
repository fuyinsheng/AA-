package com.example.missj.goaduch.com.example.missj.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miss.j on 2017/10/29.
 */

public abstract class SQLiteDALBase implements SQLiteHelper.SQLiteDataTable {
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;
    public SQLiteDALBase(Context pContext)
    {
        mContext = pContext;
    }
protected  Context getContext()
{
    return  mContext;
}
public  SQLiteDatabase getSQLiteDatabase()
{
    if(mSQLiteDatabase == null)
    {
        mSQLiteDatabase = SQLiteHelper.GetInstance(mContext).getWritableDatabase();
    }
    return  mSQLiteDatabase;
}
public void StartTransation()
{
    mSQLiteDatabase.beginTransaction();
}
public  void  SetTransactionSuccessful()
{
    mSQLiteDatabase.setTransactionSuccessful();
}
public  void  EndTransaction()
{
    mSQLiteDatabase.endTransaction();
}
public  int getCount(String pCondtiton)
{
    String[] _String = getTableNameAndPK();
    Cursor _Cursor = ExecSql("Select"+_String[1]+" From "+_String[0] +" Where 1=1 "
                +pCondtiton);
    int _Count = _Cursor.getCount();
    _Cursor.close();
    return  _Count;
}

    public  int GetCount(String pPK, String pTableName, String pCondition)
    {
        Cursor _Cursor = ExecSql("Select "+pPK+ " From "+pTableName+" Where 1=1 "+pCondition);
        int _Count = _Cursor.getCount();
        _Cursor.close();
        return  _Count;
    }
    protected  Boolean Delete(String pTableName, String pCondition)
    {
        return  getSQLiteDatabase().delete(pTableName, " 1=1 "+pCondition,null)>=0;
    }
  protected  abstract  String[] getTableNameAndPK();
protected  List GetList(String pSqlText)
{
    Cursor _Cursor = ExecSql(pSqlText);
    return  CursorToList(_Cursor);
}
   protected  abstract  Object FindModel(Cursor pCursor);
protected  List CursorToList(Cursor pCursor)
{
    List _List = new ArrayList();
    while (pCursor.moveToNext())
    {
        Object _object = FindModel(pCursor);
        _List.add(_object);

    }
    pCursor.close();
    return _List;
}
public Cursor ExecSql(String pSqlText)
{
    return  getSQLiteDatabase().rawQuery(pSqlText, null);
}
}
