package Information.control;

import Information.model.UserDAO;
import Information.model.UserDAOImp;
import Information.model.javabean.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImp();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        switch (method){
            case "login":{
                login(request,response);
                break;
            }
            case "logoff":{
                logoff(request,response);
                break;
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //写登录的方法
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (request.getParameter("username")!=null&&request.getParameter("password")!=null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //查询数据库中有没有这个账户密码对应的用户信息
            User u = userDAO.login(username, password);

            if (u != null) {
                //在登录成功的分支里判断用户是否要保存用户名和密码到cookie
                String[] values = request.getParameterValues("rememberMe");
                if (values == null) {
                } else {
                    Cookie usernameCookie = new Cookie("username", username);
                    usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(usernameCookie);
                }
                //成功后跳转页面的方法
                request.getSession().setAttribute("user", u);
                request.getRequestDispatcher("LinkMan.jsp").forward(request, response);
            } else {
                //失败时的显示
                request.setCharacterEncoding("utf-8");
                response.setCharacterEncoding("utf-8");
                request.setAttribute("errorMessage", "用户名或者密码错误！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else {
            //一周免登录
            String usernmae=null;
            Cookie[] cs = request.getCookies();
            for (Cookie c:cs){
                if (c.getName().equals("username")){
                    usernmae=c.getValue();
                    System.out.println(usernmae);
                    break;
                }
            }
            User u = userDAO.login(usernmae);

            request.getSession().setAttribute("user",u);
            request.getRequestDispatcher("LinkMan.jsp").forward(request,response);

        }

        }

    protected void logoff(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //销毁session里的用户信息

        request.getSession().removeAttribute("user");
        //清除cookie

        Cookie[] cs=request.getCookies();
        for(Cookie c: cs){
            if(c.getName().equals("username")){
                c.setValue(null);
                c.setMaxAge(0);
                response.addCookie(c);
                break;
            }
        }

        response.sendRedirect("index.jsp");
    }
    }
