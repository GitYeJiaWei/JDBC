package itheima;

import itheima.dao.UserDao;
import itheima.daoImpl.UserDaoImpl;
import itheima.util.JDBCUtil;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用junit执行单元测试
 */
public class TestDemo {
    @Test
    public void testDao(){
        UserDao userDao = new UserDaoImpl();
        userDao.findAll();
    }

    @Test
    public void testQuery() {
        // 查询
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement，跟数据库打交道必用
            st = conn.createStatement();

            // 3. 执行sql语句，返回ResultSet
            String sql = "select * from tb_stu1";
            rs = st.executeQuery(sql);

            // 4. 遍历结果集
            while (rs.next()) {
                String name = rs.getString("name");
                int sex = rs.getInt("sex");

                System.out.println(name + "   " + sex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st, rs);
        }

    }

    @Test
    public void testInsert(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "insert into tb_stu1 values(null , 'aobama' , 23 , '2015-08-13')";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st);
        }

    }

    @Test
    public void testDelete(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "delete from tb_stu1 where name='aobama'";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st,null);
        }

    }

    @Test
    public void testUpdate(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "update tb_stu1 set sex = 26 where name ='sew'";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st,null);
        }

    }
}
