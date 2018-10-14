package servlet.food;

import dao.foodDAO;
import dao.impl.foodDAOImpl;
import dao.impl.userDAOimpl;
import dao.userDAO;
import filter.flavourFilter;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.sf.json.JSONObject;
import recommend.itemCF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * 为多个人进行推荐
 */
@WebServlet(name = "recommendFoodServlet")
public class recommendFoodServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public recommendFoodServlet(){

    }
    public recommendFoodServlet(JSONObject jsonObject){
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

        int num_people=jsonObject.getInt("num_people");
        int num_food=jsonObject.getInt("num_food");
//        String avoid_food=jsonObject.getString("avoid_food");
        String suit_people=jsonObject.getString("suit_people");

        String userId=jsonObject.getString("userId");
        String foodId=jsonObject.getString("foodId");


        foodDAO foodDAO=new foodDAOImpl();

        itemCF itemCF=new itemCF();
        List recommendList=itemCF.getItem(userId,foodId);//获取到对该用户推荐的菜谱

        List suitPeopleList=foodDAO.getSuitPeopleFood(suit_people);//推荐给特定人群的菜谱

        //将两个推荐列表合并
        for (int i=0;i<suitPeopleList.size();i++){
            recommendList.add(suitPeopleList.get(i));
        }

        String userFlavour=jsonObject.getString("userFlavour");//获取前台用户输入的口味信息
        if (userFlavour==null||userFlavour==""){
            //如果用户没有选择口味信息则不进行操作
        }else{
            int[] flavour=new int[4];
            for (int i=0;i<userFlavour.length();i++){
                flavour[i]=(int)userFlavour.charAt(i);
            }
            recommendList=new flavourFilter().doFilterByFlavour(recommendList);
        }

        String avoidFoodType=jsonObject.getString("avoidFoodType");
        if (avoidFoodType==null||avoidFoodType==""){
            //如果用户没有对海鲜猪肉等的忌口则不进行筛选
        }else {
            recommendList=new flavourFilter().doFilterByAvoidFood(recommendList,avoidFoodType);
        }


        JSONObject recommend=new JSONObject();
        userDAO userDAO=new userDAOimpl();
        for (int i=0;i<recommendList.size();i++) {
            String tmp=userDAO.getFoodInfoById((String) recommendList.get(i));
            System.out.println("foodId为"+recommendList.get(i)+"的菜品的信息为："+tmp);
            recommend.put(recommendList.get(i)+"",tmp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }
}
