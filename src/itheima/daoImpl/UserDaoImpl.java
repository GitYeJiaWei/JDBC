package itheima.daoImpl;

import itheima.dao.UserDao;
import itheima.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @description 这是一个Dao的实现类
 * @author Mr.Ye
 * @date 2019/7/16
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void findAll() {
        Connection conn = null;
        Statement st =null;
        ResultSet rs = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConn();
            //创建statement
            st = conn.createStatement();

            String sql = "select * from user_test";
            rs = st.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String account = rs.getString("account");
                String password = rs.getString("password");

                System.out.println("id="+id+"account="+account+"password="+password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,st,rs);
        }

    }

    @Override
    public void login(String name, String password) {

    }
}
