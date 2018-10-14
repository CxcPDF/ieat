package servlet.user;

import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import config.*;
import servlet.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 根据useId来获取用户喜欢的菜品信息
 */
@WebServlet(name = "getUserCollectionServlet")
public class getUserCollectionServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public getUserCollectionServlet(){}

    public getUserCollectionServlet(JSONObject jsonObject){
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

        String userId=jsonObject.getString(Config.USERID);
        CommonResponse res=new CommonResponse();
        userDAO userDAO=new userDAOimpl();
        String data=userDAO.getUserCollection(userId);
        responseJSON=res.setResult(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        

    }
}
