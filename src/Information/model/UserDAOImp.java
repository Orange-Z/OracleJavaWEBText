package Information.model;

import Information.model.javabean.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImp extends BaseDAOImp implements UserDAO {
    @Override
    public User login(String username, String password) {
        String loginSQL = "select * from user where username=? and password=?";
        User u = null;
        try {
            PreparedStatement pre=getPre(loginSQL);
            pre.setString(1,username);
            pre.setString(2,password);
            ResultSet rs= pre.executeQuery();
            if(rs.next()) {
                u=new User();
                u.setUserId(rs.getInt("userId"));
                u.setUsername(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
     }

    @Override
    public User login(String username) {
        String loginSQL="select * from user where username=?";
        User u = null;
        try{
            PreparedStatement pre = getPre(loginSQL);
            pre.setString(1,username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setUsername(rs.getString("username"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}
