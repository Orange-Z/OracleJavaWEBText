package Information.model;

import Information.model.javabean.User;


public interface UserDAO {
    public User login(String username, String password);//调用时都要建立一次数据库链接
    public User login(String username);//调用时都要建立一次数据库链接
}
