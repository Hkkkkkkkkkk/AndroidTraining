package com.example.androidtraining.DBHelper;

import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.Dao.UserDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public interface NoteDAOService<T,Z> {
    int createAll(List<T> t) throws SQLException;



    int deleteAll(List<T> t) throws SQLException;
    int update(T t) throws SQLException;
    T queryForId(Z z) throws SQLException;
    List<T> queryAll() throws SQLException;
    List<T> Vague(Z z) throws SQLException;


}
