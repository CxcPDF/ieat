package servlet.user;

import bean.user;
import config.Config;
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

@WebServlet(name = "UserUpdateInfoServlet")
public class UserUpdateInfoServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;


    public UserUpdateInfoServlet(){}

    public UserUpdateInfoServlet(JSONObject jsonObject){
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");




        CommonResponse res = new CommonResponse();
        userDAO userDAO = new userDAOimpl();
        if (userDAO.updateUserInfo(jsonObject)){
            JSONObject json=new JSONObject();
            json.put(Config.NOTICE,Config.SUCCESS_TO_UPDATE);
            responseJSON=res.setResult(json.toString());
        } else {
            System.out.println("更新失败！");
//            responseJSON.put("notice","注册失败，请稍候重试！！！");
            JSONObject json=new JSONObject();
            json.put(Config.NOTICE,Config.FAIL_TO_UPDATE);
            responseJSON=res.setResult(json.toString());
        }

    }
}
