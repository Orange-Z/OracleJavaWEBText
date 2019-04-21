package Information.model;

import java.sql.*;

public class BaseDAOImp implements BaseDAO {

    private Connection con;
    private PreparedStatement pre;
    private Statement sta;

    public Connection getCon(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url,username,password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public PreparedStatement getPre(String sql){
        if(con == null){
            con = getCon();
        }
        try{
            pre = con.prepareStatement(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return pre;
    }

    public Statement getSta(){
        if(con == null){
            con = getCon();
        }
        try{
            sta = con.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sta;
    }

    public void disposeResource(Connection con, Statement sta, PreparedStatement pre, ResultSet rs){

    }

}
