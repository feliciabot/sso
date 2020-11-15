import jwt.JWT;
import jwt.TokenState;
import net.minidev.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class sendSource extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("正在调用获取信息的接口");
        String token= request.getParameter("token");

//        String username=request.getParameter("username");//修改到后面token解析出username

        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");

        //检验JWT合法性
        System.out.println("开始校验token");
        //从请求头中获取token
//         String token=request.getHeader("token");
//         String LOCAL_SERVICE=request.getHeader("LOCAL_SERVICE");
//        String token=null;
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie:cookies){
//            if(cookie.getName().equals("token")){
//                token=cookie.getValue();
//            } else{
//                System.out.println("未取得有效token");
//            }
//        }
        System.out.println("token"+token);
        String username=JWT.getUsername(token);
        Map<String, Object> resultMap= jwt.JWT.validToken(token);
        TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
        switch (state) {
            case VALID:
                //取出payload中数据,放入到request作用域中
                request.setAttribute("data", resultMap.get("data"));
                //放行
                Date date=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String BooksJson="Hello,"+username+".Time:"+formatter.format(date);  //BooksJson为模拟的资源
                //资源为json格式的数据
                request.setAttribute("BooksJson", BooksJson);
                request.setAttribute("username", username);
                request.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);
                System.out.println("sendSource-LOCAL_SERVICE:"+LOCAL_SERVICE);
                request.getRequestDispatcher("/WEB-INF/jsp/sendSource.jsp").forward(request, response);

                break;
            case EXPIRED:
            //无效token
            case INVALID:
                System.out.println("无效token");

                //token过期或者无效，则输出错误信息返回给ajax
//                JSONObject outputMSg=new JSONObject();
//                outputMSg.put("success", false);
//                outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
                //output(outputMSg.toJSONString(), response);
                //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                break;
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
