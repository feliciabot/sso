import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class app extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fail = request.getParameter("fail");
        System.out.println("fail1="+fail);
        if(fail!=null){
            request.setAttribute("fail",fail);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检查是否登录
        boolean flag = false;
        String token ="";

        String fail = request.getParameter("fail");
        System.out.println("fail2="+fail);
        if(fail!=null){
            request.setAttribute("fail",fail);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }

        String LOCAL_SERVICE="";
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")) {
                    //验证token是否有效
                    flag = true;
                    token =cookie.getValue();
                    break;
                }
            }
        }
        //若已登录
        if(flag){
//            request.setAttribute("username",username);
            //用JWT去服务端获取资源
            request.setAttribute("token",token);
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
