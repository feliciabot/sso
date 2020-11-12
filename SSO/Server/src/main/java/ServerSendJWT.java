import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerSendJWT extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");

        if(username.equals("123")&&password.equals("123")){  //匹配
             String JWT="iamjwt";
             request.setAttribute("JWT", JWT);
            request.setAttribute("username", username);
            request.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);
            System.out.println("ServerSendJWT-LOCAL_SERVICE:"+LOCAL_SERVICE);
            request.getRequestDispatcher("/WEB-INF/jsp/ServerSendJWT.jsp").forward(request, response);
        }else{  //不匹配

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
