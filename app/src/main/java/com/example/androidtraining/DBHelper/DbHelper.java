package com.example.androidtraining.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidtraining.Dao.TypeDao;
import com.example.androidtraining.Dao.UserDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper{
    private static final String dbName = "contacts.db";
    private static final int version = 2;
    private static DbHelper getDatabase;
    public  static synchronized DbHelper getDatabase(Context context){
        if (getDatabase==null){
            getDatabase=new DbHelper(context.getApplicationContext());
        }
        return getDatabase;
    }
    public DbHelper(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserDao.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,UserDao.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
