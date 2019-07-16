package itheima.test;

import itheima.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainTest {
    public static void main(String[] args){
        Connection conn = null;
        Statement state = null;
        ResultSet res = null;

        try {
            //Ctrl+Alt+T 捕获异常快捷键
            //1,注册驱动,连接数据库
            conn = JDBCUtil.getConn();

            //3.创建statement，跟数据库打交道一定需要这个对象
            state = conn.createStatement();

            //4.执行查询，得到结果集
            String sql = "select * from tb_stu1";
            res = state.executeQuery(sql);

            //5.遍历查询每一条记录
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                int sex = res.getInt("sex");

                //sout简写方式
                System.out.println("id="+id + "===name="+name+"==sex="+sex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,state,res);
        }

    }
}
