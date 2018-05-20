package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.BaseUser;
import bean.Information;
/**
 * Created by Westchennn on 17/3/18.
 */
public class GetData {

    private static ResultSet ret = null;
    private ResultSetMetaData rsmd = null;

    public List<Map<String,Object>> getPrice(String sql){
        DBManager db = new DBManager(sql);
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            ret = db.pst.executeQuery();//得到结果集
            rsmd = ret.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (ret.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1 ; i <= columnCount ; i++) {
                    map.put(rsmd.getColumnLabel(i), ret.getObject(i));
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            db.close();
        }
        return null;
    }

    public List<Map<String, Object>> getUser() {

        String sql = null;

        sql = "select * from user_data";// 从用户数据表中全部获取的SQL语句

        DBManager db = new DBManager(sql);
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            ret = db.pst.executeQuery();//得到结果集
            rsmd = ret.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (ret.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1 ; i <= columnCount ; i++) {
                    map.put(rsmd.getColumnLabel(i), ret.getObject(i));
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            db.close();
        }
        return null;
    }

    public List<Map<String, Object>> getInfo2(String sql) {

        DBManager db = new DBManager(sql);
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            ret = db.pst.executeQuery();//执行语句得到结果集
            rsmd = ret.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String bookName = null;
            while (ret.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    if (rsmd.getColumnLabel(i).equals("bookName")) {
                        bookName = (String) ret.getObject(i);
                    } else {
                        map.put(rsmd.getColumnLabel(i), ret.getObject(i));
                    }
                }
                if (bookName != null)
                    map = this.getInfoItem("select bookPrice from info where bookName = " + bookName, map);
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            db.close();
        }
        return null;
    }

    public Map<String, Object> getInfoItem(String sql, Map<String, Object> map) {

        DBManager db = new DBManager(sql);

        try {
            ResultSet ret = db.pst.executeQuery();// 执行语句得到结果集
            ResultSetMetaData rsmd = ret.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (ret.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), ret.getObject(i));
                }
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            db.close();
        }
        return null;
    }

    public Map<String, Object> getInfoItem2(String sql, Map<String, Object> map) {

        DBManager db = new DBManager(sql);
        String bookName = null;

        try {
            ResultSet ret = db.pst.executeQuery();// 执行语句得到结果集
            ResultSetMetaData rsmd = ret.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (ret.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if (rsmd.getColumnLabel(i).equals("bookName")) {
                        bookName = (String) ret.getObject(i);
                        map = this.getInfoItem("select bookPrice from info where name = '" + bookName + "'", map);
                    }
                }
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            db.close();
        }
        return null;
    }
}
