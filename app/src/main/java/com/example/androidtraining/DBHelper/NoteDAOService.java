package com.example.androidtraining.DBHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public interface NoteDAOService<T,Z> {
    int create(T t) throws SQLException;
    int delete(T t) throws SQLException;
    int update(T t) throws SQLException;
    T queryForId(Z z) throws SQLException;
    List<T> queryAll() throws SQLException;
    List<T> Vague(Z z) throws SQLException;
}
