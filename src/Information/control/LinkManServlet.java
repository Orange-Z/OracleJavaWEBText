package Information.control;
import Information.model.LinkmanDAO;
import Information.model.LinkmanDAOImp;
import Information.model.javabean.Linkman;
import Information.model.javabean.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LinkManServlet",urlPatterns = "/LinkManServlet")
public class LinkManServlet extends HttpServlet {

    private LinkmanDAO linkmanDAO;
    public void init() throws ServletException{
        linkmanDAO=new LinkmanDAOImp();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        String method=request.getParameter("method");

        switch (method){
            case "listLinkMan":{
                listLinkMan(request,response);
                break;
            }
        }
    }
    //根据用户id和分页显示数量取出对应的联系人集合
    protected void listLinkMan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userId=request.getParameter("userId");
        String page=request.getParameter("page");
        String count=request.getParameter("count");
        //封装PageBean对象，计算分页
        PageBean p=new PageBean();
        p.setAllCount(linkmanDAO.getAllCountLinkman(Integer.parseInt(userId)));
        int allPages=p.getAllCount()%Integer.parseInt(count)==0?p.getAllCount()/Integer.parseInt(count):p.getAllCount()/Integer.parseInt(count)+1;
        p.setAllPages(allPages);
        p.setFirstPage(1);
        p.setNowPage(Integer.parseInt(page));
        p.setNextPage(p.getNowPage()==p.getAllPages()?p.getAllPages():p.getNowPage()+1);
        p.setPreviousPage(Integer.parseInt(page)>1?Integer.parseInt(page)-1:1);
        p.setEveryPageCount(Integer.parseInt(count));

        request.setAttribute("pageBean",p);


        try {
            List<Linkman> linkmans;
            linkmans = linkmanDAO.listMan(Integer.parseInt(userId),Integer.parseInt(page),Integer.parseInt(count));

            request.setAttribute("allLinkman",linkmans);
            request.getRequestDispatcher("LinkMan.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }


}
