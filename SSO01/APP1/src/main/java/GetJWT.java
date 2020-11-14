import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class GetJWT extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到 JWT
        String token=request.getParameter("token");
        System.out.println("token:"+token);

        //获取账号密码
        String username = request.getParameter("username");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
        request.setAttribute("username", username);
        request.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);


        //String username = request.getParameter("username");
        //String resultJSON = URLEncoder.encode(resultJSON,"utf-8");
        Cookie cookie =new Cookie("token",token);
        System.out.println("token="+token);
        //设置cookie一个小时后过期
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        //用JWT去服务端获取资源
        request.setAttribute("token",token);
        request.getRequestDispatcher("/WEB-INF/jsp/getSource.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
