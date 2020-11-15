import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Dao.UserDao;
import Entity.User;

@WebServlet(name = "Register",urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
//        User user=new User();
//        user.setUsername(username);
//        user.setPasswd(password);
        //UserDao ad = new UserDao();
        User user= new User();
        user.setUsername(username);
        user.setPasswd(password);
        try {
            UserDao.add(user);
            request.getRequestDispatcher("/WEB-INF/jsp/SelectApp.jsp").forward(request, response);

        }  catch (Exception e) {
            request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp"+"?fail=loginError").forward(request, response);
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);
    }
}
