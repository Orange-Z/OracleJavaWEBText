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

    //д��¼�ķ���
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (request.getParameter("username")!=null&&request.getParameter("password")!=null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //��ѯ���ݿ�����û������˻������Ӧ���û���Ϣ
            User u = userDAO.login(username, password);

            if (u != null) {
                //�ڵ�¼�ɹ��ķ�֧���ж��û��Ƿ�Ҫ�����û��������뵽cookie
                String[] values = request.getParameterValues("rememberMe");
                if (values == null) {
                } else {
                    Cookie usernameCookie = new Cookie("username", username);
                    usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(usernameCookie);
                }
                //�ɹ�����תҳ��ķ���
                request.getSession().setAttribute("user", u);
                request.getRequestDispatcher("LinkMan.jsp").forward(request, response);
            } else {
                //ʧ��ʱ����ʾ
                request.setCharacterEncoding("utf-8");
                response.setCharacterEncoding("utf-8");
                request.setAttribute("errorMessage", "�û��������������");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else {
            //һ�����¼
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
        //����session����û���Ϣ

        request.getSession().removeAttribute("user");
        //���cookie

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
