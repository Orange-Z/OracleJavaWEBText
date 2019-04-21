package Information.model;

import Information.model.javabean.Linkman;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkmanDAOImp extends  BaseDAOImp implements LinkmanDAO{
    @Override
    public List<Linkman> listMan(int userId,int page,int count) {
        List<Linkman> bs=new ArrayList<>();
        try {
            ResultSet rs=getSta().executeQuery("select *  from information where userId="+userId+" limit "+(page-1)*count+","+count);
            while(rs.next()){
                Linkman b=new Linkman();
                b.setLinkmanId(rs.getInt("linkmanId"));
                b.setName(rs.getString("name"));
                b.setSex(rs.getString("sex"));
                b.setPhone(rs.getString("phone"));
                b.setEmail(rs.getString("email"));
                b.setGroup(rs.getString("groupo"));

                bs.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bs;
    }



    @Override
    public int getAllCountLinkman(int userId) {
        int result=0;
        ResultSet rs=null;
        try {
            rs=getSta().executeQuery("select count(linkmanId) from information where userId="+userId);
            rs.next();
            result=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
