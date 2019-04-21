package Information.model;

import Information.model.javabean.Linkman;


import java.util.List;

public interface LinkmanDAO {
    public List<Linkman> listMan(int userId,int page,int count);
    public int getAllCountLinkman(int userId);


}
