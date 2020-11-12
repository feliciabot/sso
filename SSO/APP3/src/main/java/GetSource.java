import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到资源
        String BooksJson=request.getParameter("BooksJson");
        System.out.println("BooksJson:"+BooksJson);

        //展示资源
        request.setAttribute("BooksJson",BooksJson);
        request.getRequestDispatcher("/WEB-INF/jsp/showSource.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
