package com.example.androidtraining.DBHelper;

import android.content.Context;

import com.example.androidtraining.Dao.UserDao;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public class NoteDAOServiceImpl<T,Z> implements NoteDAOService<T,Z> {
    private Context context;
    private Class<T> aClass;
    //    缓存DAO层
    private Map<Class<T>,Dao<T,Z>> daoMap = new HashMap<>();

    public NoteDAOServiceImpl(Context context, Class<T> aClass) {
        this.context = context;
        this.aClass  = aClass;
    }
    //    获取Helper dao 帮助类
    private Dao<T,Z> getDao()throws SQLException{
        Dao<T,Z> dao = daoMap.get(aClass);
        if (dao == null){
            dao = DbHelper.getDatabase(context).getDao(aClass);
            daoMap.put(aClass,dao);
        }
        return dao;
    }
    @Override
    public int createAll(final List<T> userDaos) throws SQLException {
        final Dao<T,Z> dao =getDao();
        DatabaseConnection connection = null;
        try {
            //开启数据库事物
            connection = dao.startThreadConnection();
            //设置为手动提交

            dao.setAutoCommit(connection,false);
            try {
                dao.callBatchTasks(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        for (T article : userDaos) {
                            dao.create(article);
                        }

                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            //向表中添加一条数据

            //提交数据
            dao.commit(connection);
            //关闭提交

        }catch (SQLException e){
            //添加错误，回滚数据库
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            //结束事物
            dao.endThreadConnection(connection);
        }
        return 0;
    }


    @Override
    public int deleteAll(final List<T> t) throws SQLException {
        final Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            try {
                dao.callBatchTasks(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        for (T article : t) {
                            dao.delete(article);
                        }

                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            dao.commit(connection);

        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int save = dao.update(t);
            dao.commit(connection);
            return save;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    @Override
    public T queryForId(Z z) throws SQLException {
        Dao<T,Z> dao  = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            T t = dao.queryForId(z);
            dao.commit(connection);
            return t;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }

    @Override
    public List queryAll() throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            dao.commit(connection);
            return dao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
            dao.rollBack(connection);
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }

    @Override
    public List<T> Vague(Z z) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            List<T> query = dao.queryBuilder().where().eq("tid", z).query();
            dao.commit(connection);
            return query;
        }catch (SQLException e){
            e.printStackTrace();
            dao.rollBack(connection);
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }
}
