package servlet.food;

import dao.foodDAO;
import dao.impl.foodDAOImpl;
import dao.impl.userDAOimpl;
import dao.userDAO;
import filter.flavourFilter;
import net.sf.json.JSONObject;
import recommend.itemCF;
import util.GetUserPreference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 为多个人进行推荐
 */
@WebServlet(name = "recommendFoodServlet")
public class recommendFoodServlet extends HttpServlet {

    public JSONObject jsonObject = null;
    public JSONObject responseJSON = null;

    public recommendFoodServlet() {
    }

    public recommendFoodServlet(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId = jsonObject.getString("userId");//用户id
        int num_people = Integer.parseInt(jsonObject.getString("num_people"));//用餐人数
        int num_food = Integer.parseInt(jsonObject.getString("num_food"));//菜式数量
        String suit_people = jsonObject.getString("suit_people");//适宜人群
        String avoidFoodType = jsonObject.getString("avoidFoodType");//忌口
        List foodList = new ArrayList();

        try {
            foodList = new GetUserPreference().getFoodId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String userFlavour = null;


        foodDAO foodDAO = new foodDAOImpl();
        List recommendList = new ArrayList();
        for (int i = 0; i < foodList.size(); i++) {
            System.out.println(foodList.get(i));
            List tempList = null;//获取到对该用户推荐的菜谱
            try {
                tempList = itemCF.getItem(userId, (String) foodList.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            recommendList.addAll(tempList);
        }
        List suitPeopleList = foodDAO.getSuitPeopleFood(suit_people);//推荐给特定人群的菜谱
        //将两个推荐列表合并
        recommendList.addAll(suitPeopleList);
        if (userFlavour == null || userFlavour == "") {
            //如果用户没有选择口味信息则不进行操作
        } else {
            int[] flavour = new int[4];
            for (int i = 0; i < userFlavour.length(); i++) {
                flavour[i] = (int) userFlavour.charAt(i);
            }
            recommendList = new flavourFilter().doFilterByFlavour(recommendList);
        }

        if (avoidFoodType == null || avoidFoodType == "") {
            //如果用户没有对海鲜猪肉等的忌口则不进行筛选
        } else {
            recommendList = new flavourFilter().doFilterByAvoidFood(recommendList, avoidFoodType);
        }


        JSONObject recommend = new JSONObject();
        userDAO userDAO = new userDAOimpl();
        for (int i = 0; i < recommendList.size(); i++) {
            String tmp = userDAO.getFoodInfoById((String) recommendList.get(i));
            System.out.println("foodId为" + recommendList.get(i) + "的菜品的信息为：" + tmp);
            recommend.put(recommendList.get(i) + "", tmp);
        }

        for (int i = 0; i < recommendList.size(); i++) {
            responseJSON.put("recommend", recommendList.get(i));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }
}
