import Dao.UserDao;
import Entity.User;
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
        System.out.println(LOCAL_SERVICE);

        try{
            User user = UserDao.get(username);
            if(user!=null){
                if(user.getPasswd().equals(password)){
                    System.out.println(user.getUsername());
                    Map<String , Object> payload=new HashMap<String, Object>();
                    Date date=new Date();
                    payload.put("uid", username);//用户ID
                    payload.put("iat", date.getTime());//生成时间
                    payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
                    String token=JWT.createToken(payload);

                    request.setAttribute("token",token );
                    System.out.println("token");
                    request.setAttribute("username", username);
                    request.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);
                    System.out.println("ServerSendJWT-LOCAL_SERVICE:"+LOCAL_SERVICE);
                    request.getRequestDispatcher("/WEB-INF/jsp/ServerSendJWT.jsp").forward(request, response);
                }else{
                    System.out.println("用户密码，返回login页面");
                    response.sendRedirect(LOCAL_SERVICE+"app"+"?fail=loginError");
                }
            }else{
//                response.sendRedirect(LOCAL_SERVICE+"?info=YourUsernameOrPasswordIsWrong.");
                System.out.println("用户登陆失败，返回login页面");
                response.sendRedirect(LOCAL_SERVICE+"app"+"?fail=loginError");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
