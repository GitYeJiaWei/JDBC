package itheima.dao;
/**
 * @description 定义操作数据库的方法
 * @author Mr.Ye
 * @date 2019/7/16
 */
public interface UserDao {
    /**
     * 查询所有
     */
    void findAll();

    void login(String name ,String password);
}
