import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sendSource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String JWT = request.getParameter("JWT");
        String username=request.getParameter("username");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
        //检验JWT合法性
        if(JWT.equals("iamjwt")){  //JWT合法
            //JWT合法就发送资源
            String BooksJson="i_am_source";  //BooksJson为模拟的资源
            //资源为json格式的数据
            request.setAttribute("BooksJson", BooksJson);
            request.setAttribute("username", username);
            request.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);
            System.out.println("sendSource-LOCAL_SERVICE:"+LOCAL_SERVICE);
            request.getRequestDispatcher("/WEB-INF/jsp/sendSource.jsp").forward(request, response);
        }else{  //JWT不合法

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
