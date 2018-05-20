package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Created by Westchennn on 17/3/18.
 */
public class DBManager {
    //数据库名、用户名、密码
    public static final String url = "jdbc:mysql://localhost:3306/jingDongTest";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "2222";

    public Connection  conn = null;
    public PreparedStatement pst = null;

    public DBManager(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //断开数据库连接
    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
