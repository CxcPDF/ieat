package servlet.user;

import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import servlet.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getUserPublishServlet")
public class getUserPublishServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public getUserPublishServlet(){}

    public getUserPublishServlet(JSONObject jsonObject){
        this.jsonObject=jsonObject;
        responseJSON=new JSONObject();
    }


    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            this.doPost(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("你好"+responseJSON.toString());
        return responseJSON;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String userId=jsonObject.getString("userId");
        userDAO userDAO=new userDAOimpl();
        CommonResponse res=new CommonResponse();
        String data=userDAO.getPublishFood(userId);
        responseJSON=res.setResult(data);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }
}
