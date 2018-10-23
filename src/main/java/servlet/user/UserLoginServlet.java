package servlet.user;

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
import java.sql.SQLException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    /**
     * 从客户端发来的请求数据
     */
    private JSONObject requestParam = null;

    /**
     * 服务器端返回给客户端的数据
     */
    private JSONObject responseJSON = null;


    public UserLoginServlet() {

    }

    public UserLoginServlet(JSONObject jsonObject) {
        this.requestParam = jsonObject;
        responseJSON = new JSONObject();
    }

    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJSON;
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String account = requestParam.getString("account");
        String password = requestParam.getString("password");
        String type = requestParam.getString("login_type");

        CommonResponse res = new CommonResponse();


        userDAO userDAO = new userDAOimpl();
        try {
            String oauthAccessToken = userDAO.getAccess(account, type);
            if (oauthAccessToken != null && oauthAccessToken.equals(password)) {
                System.out.println("登录成功！！");
                String userId=userDAO.getUserId(account);

//                responseJSON.put("notice", "登陆成功！！！");
                JSONObject json=new JSONObject();
                json.put(Config.NOTICE,Config.SUCCESS_TO_LOGIN);
                json.put(Config.STATUSCODE,Config.SUCCESS);
                json.put(Config.USERID,userId);
                responseJSON=res.setResult(json.toString());

            } else {
                System.out.println("账号或密码错误，请检查后重新登陆");
                JSONObject json=new JSONObject();
                json.put(Config.STATUSCODE,Config.FAIL);
                json.put(Config.NOTICE,Config.FAIL_TO_LOGIN);

                responseJSON=res.setResult(json.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
