package com.example.androidtraining.Dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public class TypeDao {

    private int id;

    private String name;

    public List<UserDao> getUserDaoList() {
        return userDaoList;
    }

    public TypeDao(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setUserDaoList(List<UserDao> userDaoList) {
        this.userDaoList = userDaoList;
    }

    private List<UserDao> userDaoList;
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
}
