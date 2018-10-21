package servlet.user;

import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import servlet.CommonResponse;
import config.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户登录成功后返回给用户基本信息
 *
 */
@WebServlet(name = "UserInfoServlet")
public class UserInfoServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public UserInfoServlet(){}

    public UserInfoServlet(JSONObject jsonObject){
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
        return responseJSON;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId=jsonObject.getString("userId");

        CommonResponse res=new CommonResponse();
        userDAO userDAO=new userDAOimpl();

        JSONObject userInfo=userDAO.getUserInfo(userId);
        responseJSON=res.setResult(userInfo.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


    }
}
