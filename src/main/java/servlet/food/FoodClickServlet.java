package servlet.food;

import bean.actionPre;
import dao.actionPreDAO;
import dao.impl.actionPreDAOimpl;
import net.sf.json.JSONObject;
import servlet.CommonResponse;
import config.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "FoodClickServlet")
public class FoodClickServlet extends HttpServlet {
    private JSONObject requestParam=null;
    private JSONObject responseJSON=null;

    public FoodClickServlet(){

    }

    public FoodClickServlet(JSONObject jsonObject){
        this.requestParam=jsonObject;
        responseJSON=new JSONObject();
    }
    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.doPost(request,response);
        }catch (ServletException e){
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String userId= requestParam.getString("userId");
        System.out.println(userId);
        String foodId= requestParam.getString("foodId");
        String isCollection= requestParam.getString("isCollection");
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String clickTime = sdf.format(time);

        /*
        * 假的时间clickTime1
        * */
        String clickTime1=requestParam.getString("clickTime");

        String star= requestParam.getString("star");
        CommonResponse res=new CommonResponse();
        actionPreDAO actionPreDAO=new actionPreDAOimpl();

        actionPre actionPre=new actionPre(Integer.parseInt(userId),Integer.parseInt(foodId),Boolean.parseBoolean(isCollection),clickTime,Integer.parseInt(star));
        System.out.println("userId:"+userId+"foodId:"+foodId+"isCollection"+isCollection+"time"+clickTime+"star"+star);
        if(actionPreDAO.addActionPre(actionPre)){
            System.out.println("用户行为插入成功！！！");
//            responseJSON.put("notice","actionPre插入成功！");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put(Config.NOTICE,Config.SUCCESS);
            responseJSON=res.setResult(jsonObject.toString());
        }else {
            System.out.println("行为插入失败！！！");
//            responseJSON.put("notice","actionPre插入失败！");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put(Config.NOTICE,Config.FAIL);
            responseJSON=res.setResult(jsonObject.toString());
        }


    }
}
