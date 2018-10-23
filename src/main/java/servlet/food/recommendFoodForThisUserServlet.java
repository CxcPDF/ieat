package servlet.food;

import dao.foodDAO;
import dao.impl.foodDAOImpl;
import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import recommend.itemCF;
import servlet.CommonResponse;
import util.GetUserPreference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "recommendFoodForThisUserServlet")
public class recommendFoodForThisUserServlet extends HttpServlet {
    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public List recentClickFoodList=null;

    public recommendFoodForThisUserServlet(){

    }
    public recommendFoodForThisUserServlet(JSONObject jsonObject,List clickList){
        this.jsonObject=jsonObject;
        recentClickFoodList=clickList;
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
        String foodId=jsonObject.getString("foodId");

        userDAO userDAO=new userDAOimpl();
//        itemCF itemCF=new itemCF();

        List recommendList=new ArrayList();
//        for (int i=0;i<recentClickFoodList.size();i++) {
////            List list = itemCF.getItem(userId, (String) recentClickFoodList.get(i));
//            List list = null;
//            for (int j=0;j<list.size();j++){
//                recommendList.add(list.get(j));
//            }
//        }
        try {
            recommendList= new GetUserPreference().getFoodId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        List recommendList=itemCF.getItem(userId,foodId);

        StringBuilder sb=new StringBuilder();
        for (int i=0;i<recommendList.size();i++){
            sb.append(userDAO.getFoodInfoById((String) recommendList.get(i))).append(",");
        }
        String str=sb.substring(0,sb.length()-1);
        CommonResponse res=new CommonResponse();
        responseJSON=res.setResult(str);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }
}
