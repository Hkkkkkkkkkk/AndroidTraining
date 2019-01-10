package com.example.androidtraining.Dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 黄铿 on 2019/1/9.
 */
@DatabaseTable(tableName = "t_user")
public class UserDao {
    @DatabaseField(generatedId =true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int tid;

    public UserDao(String name, int tid) {
        this.name = name;
        this.tid = tid;
    }

    public UserDao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
