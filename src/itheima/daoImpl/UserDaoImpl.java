package itheima.daoImpl;

import itheima.dao.UserDao;
import itheima.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

   /* @Override
    public void login(String name, String password) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConn();
            //创建statement
            st = conn.createStatement();
            //statement传进来的参数会进行关键字分析，不会全部当成字符串
            String sql = "select * from user_test where account = '"+name+"'and password = '"+password+"'";
            rs = st.executeQuery(sql);
            if (rs.next()){
                System.out.println("登陆成功");
            }else {
                System.out.println("登陆失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,st,null);
        }
    }*/

    @Override
    public void login(String name, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConn();
            //创建prepareStatementstatement,预先对sql语句进行语法的校验
            //?号传进来的参数不管是什么都当成字符串
            String sql = "select * from user_test where account =? and password =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);

            rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("登陆成功");
            }else {
                System.out.println("登陆失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,ps,rs);
        }
    }

    @Override
    public void insert(String account, String password) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConn();
            String sql = "insert into user_test values (null ,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,account);
            ps.setString(2,password);

            int rs = ps.executeUpdate();
            if (rs>0){
                System.out.println("插入成功");

            }else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn,ps,null);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "delete from user_test where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int rs = ps.executeUpdate();
            if (rs>0){
                System.out.println("删除成功");

            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn,ps,null);
        }
    }

    @Override
    public void update(int id, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "update user_test set password =? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,password);
            ps.setInt(2,id);

            int rs = ps.executeUpdate();
            if (rs>0){
                System.out.println("跟新成功");

            }else {
                System.out.println("跟新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn,ps,null);
        }
    }

    @Override
    public List<String> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> strings = new ArrayList<>();
        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from user_test where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            rs = ps.executeQuery();
           while (rs.next()){
               strings.add(rs.getString("account"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn,ps,null);
        }
        return strings;
    }
}
