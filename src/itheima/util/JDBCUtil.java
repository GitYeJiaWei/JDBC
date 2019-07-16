package itheima.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password = null;

    //首先添加jdbc的jar包，File->Project Structure
    //点击<Module source>  然后点击右边绿色的“+”号
    //选择第一个 JARs or directore
    static{
        try{
            //1. 创建一个属性配置对象
            Properties properties = new Properties();
            //工程位于根目录的资源位置，流的模式
            //InputStream is = new FileInputStream("jdbc.properties");

            //使用类加载器，去读取src底下的资源文件。 后面在servlet
            InputStream is = itheima.util.JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //导入输入流。
            properties.load(is);

            //读取属性
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConn(){
        Connection conn = null;
        try {
            //捕获异常快捷键 Ctrl+Alt+T
            //注册驱动，这句话也可以不添加了
            //Class.forName(driverClass);
            //静态代码块 ---> 类加载了，就执行。 java.sql.DriverManager.registerDriver(new Driver());
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //DriverManager.getConnection("jdbc:mysql://localhost/test?userDao=monty&password=greatsqldb");
            //2. 建立数据库连接 参数一： 协议 + 访问的数据库 ， 参数二： 用户名 ， 参数三： 密码。
            conn = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源，对外公开的方法，用于其他地方调用
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn , Statement st , ResultSet rs){
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }

    //重载
    public static void release(Connection conn , Statement st){
        closeSt(st);
        closeConn(conn);
    }

    private static void closeRs(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeSt(Statement st){
        try {
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
