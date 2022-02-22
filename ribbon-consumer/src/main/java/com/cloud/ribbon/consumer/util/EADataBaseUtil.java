package com.cloud.ribbon.consumer.util;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EADataBaseUtil {
  /*  private String url = "jdbc:oracle:thin:@www.xlsgrid.net:1521:xlsgrid";
    private String user = "yunxim";
    private String password = "eru43wPo";*/
    //设置参数
    public static PreparedStatement prepareStatement(Connection connection,String sql,Object values[]){
        PreparedStatement ps = null;
        try{
            ps=connection.prepareStatement(sql);
            if(values.length>0){
                for (int i = 0; i < values.length; i++) {
                    try {
                        ps.setObject(i + 1, values[i]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ps;
    }
    //查询单条数据
    public Map<String, Object> querySql(String sql,Object values[], Connection connection) {
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultSetMetaData data = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ps=prepareStatement(connection,sql,values);
            result = ps.executeQuery();
            data = result.getMetaData();
            while (result.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String colName = data.getColumnName(i);
                    String colValue = result.getString(i);
                    map.put(colName, colValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    //查询集合数据
    public List<Map> querySqlList(String sql,Object[] values, Connection connection) {
        PreparedStatement ps = null;
        ResultSet result = null;
        ResultSetMetaData data = null;
        List<Map> list = new ArrayList<Map>();
        try {
            ps=prepareStatement(connection,sql,values);
            result = ps.executeQuery();
            data = result.getMetaData();
            while (result.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String colName = data.getColumnName(i);
                    String colValue = result.getString(i);
                    map.put(colName, colValue);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    //增删改数据
    public int executeSql(String sql, Object values[], Connection connection) {
        PreparedStatement ps = null;
        int num = 0;
        try {
            ps=prepareStatement(connection,sql,values);
            num=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }
}
