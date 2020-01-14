package itheima.test;

import itheima.util.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: JDBC
 * @description: JDBC的事务提交和回滚
 * @author: Mr.Ye
 * @create: 2020-01-14 10:00
 **/
public class TestTransation {
    /**
     * 原子性  事务中包含的逻辑，不可分割
     * 一致性  事务执行前后，数据完整性
     * 隔离性  事务在执行期间不应该受到其它事物的影响
     * 持久性  事务执行成功，那么数据应该持久保存到磁盘上
     *
     * 不考虑隔离级别设置，那么会出现以下问题。
     * * 脏读
     * > 一个事务读到另外一个事务还未提交的数据
     *
     * * 不可重复读
     * > 一个事务读到了另外一个事务提交的数据 ，造成了前后两次查询结果不一致。
     *
     * * 幻读
     * > 一个事务读到了另一个事务insert的数据 ，造成前后查询结果不一致。
     */

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs = null;

    @Test
    public void testTransaction() {
        try {
            conn = JDBCUtil.getConn();

            //连接，事务默认就是自动提交的，要先关闭自动提交
            conn.setAutoCommit(false);

            String sql = "update tb_stu1 set money = money - ? where id = ?";
            ps = conn.prepareStatement(sql);

            //扣钱，扣id为1的20元
            ps.setInt(1,20);
            ps.setInt(2,1);
            ps.executeUpdate();

            //添加错误代码，测试事务回滚
            //int a = 10/0;

            //加钱，加id为2的30元
            ps.setInt(1,-30);
            ps.setInt(2,2);
            ps.executeUpdate();

            //成功则提交事务
            conn.commit();

        } catch (SQLException e) {
            try {
                //失败则回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,ps,rs);
        }
    }
}
