import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class app01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检查是否登录
        boolean flag = false;
        String username = "";
        String JWT ="";
        String LOCAL_SERVICE="";
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie:cookies){
                if(cookie.getName().startsWith("username")) {
                    flag = true;
                    username = cookie.getName().substring(8);
                    JWT=cookie.getValue();
                    break;
                }
            }
        }
        //若已登录
        if(flag){
            request.setAttribute("username",username);
            //用JWT去服务端获取资源
            request.setAttribute("JWT",JWT);
            request.setAttribute("LOCAL_SERVICE","http://localhost:8081/");
            request.getRequestDispatcher("/WEB-INF/jsp/getSource.jsp").forward(request, response);
        }
        //若还没有登录
        else{
            String info = request.getParameter("info");
            request.setAttribute("info",info);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
