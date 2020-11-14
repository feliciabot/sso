import jwt.JWT;
import net.minidev.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerSendJWT extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
        JSONObject resultJSON=new JSONObject();


        if(username.equals("123")&&password.equals("123")){  //匹配
             //String JWT="iamjwt"
            Map<String , Object> payload=new HashMap<String, Object>();
            Date date=new Date();
            payload.put("uid", "admin");//用户ID
            payload.put("iat", date.getTime());//生成时间
            payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
            String token=JWT.createToken(payload);

            //resultJSON.put("token", token);

            request.setAttribute("token",token );
            System.out.println("token");
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
