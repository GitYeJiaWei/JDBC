package itheima.dao;

import java.util.List;

/**
 * @description 定义操作数据库的方法,Dao就是声明与实现分开
 * @author Mr.Ye
 * @date 2019/7/16
 */
public interface UserDao {
    /**
     * @description 查询所有
     * @author Mr.Ye
     * @date 2019/7/18 0018
     */
    void findAll();

    /**
     * @description 登录接口
     * @author Mr.Ye
     * @date 2019/7/18 0018
     */
    void login(String name ,String password);
    
    /**
     * @description 插入数据
     * @author Mr.Ye
     * @date 2019/7/18 0018
     */
    void insert(String account,String password);

    /**
     * @description 根据id删除数据
     * @author Mr.Ye
     * @date 2019/7/18 0018
     */
    void delete(int id);
    
    /**
     * @description 根据id跟新passw
     * @author Mr.Ye
     * @date 2019/7/18 0018
     */
    void update(int id ,String password);

    List<String> find(int id);
}
