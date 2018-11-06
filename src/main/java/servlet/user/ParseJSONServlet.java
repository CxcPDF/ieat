package servlet.user;

import config.Config;
import net.sf.json.JSONObject;
import servlet.food.FoodClickServlet;
import servlet.food.getFoodByMaterialServlet;
import servlet.food.recommendFoodServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * 解析App发来的json数据
 */
@WebServlet(name = "ParseJSONServlet")
public class ParseJSONServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getOutputStream().write("aaaa".getBytes());
        response.getOutputStream().write("你好，你已经成功访问到了服务器".getBytes());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");


        System.out.println(request.getContentType());//得到客户端发过来内容的类型
        System.out.println("客户端的ip地址:" + request.getRemoteAddr());//得到客户端的ip地址
        //使用字符流来读取客户端发来的数据
        //获取App发来的报文并将其解析成json格式
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer s = new StringBuffer();
        while ((line = br.readLine()) != null) {
            s.append(line);
        }
        br.close();

        System.out.println("客户端发来的内容为:" + s.toString());
        JSONObject object = JSONObject.fromObject(s.toString());


        String request_type = object.getString(Config.REQUEST_TYPE);//获取客户端的请求类型

        JSONObject responseJSON = new JSONObject();//创建返回客户端的json数据

        switch (request_type) {
            case Config.LOGIN://登录
                System.out.println("-----------------登录----------------------");
                UserLoginServlet loginServlet = new UserLoginServlet(object);
                responseJSON = loginServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.REGISTER://注册
                System.out.println("-----------------注册----------------------");
                UserRegisterServlet registerServlet = new UserRegisterServlet(object);
                responseJSON = registerServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.GETUSERIDBYACCOUNT://通过用户的account来获取userId
                System.out.println("-----------------通过用户的account来获取userId----------------------");
                getUserIdByAccountServlet getUserIdByAccountServlet = new getUserIdByAccountServlet(object);
                responseJSON = getUserIdByAccountServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.FOOD_CLICK://食物点击
                System.out.println("-----------------食物点击----------------------");
                FoodClickServlet foodClickServlet = new FoodClickServlet(object);
                responseJSON = foodClickServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.UPDATE_ACTION://更新
                System.out.println("-----------------动作更新----------------------");
                UpdateActionServlet updateActionServlet = null;
                try {
                    updateActionServlet = new UpdateActionServlet(object);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                responseJSON=updateActionServlet.getResponse(request,response);
                break;

            case Config.GETFOODINFOBYID://通过foodId来获取菜品信息
                System.out.println("-----------------通过foodId来获取菜品信息----------------------");
                getFoodInfoByIdServlet getFoodInfoByIdServlet = new getFoodInfoByIdServlet(object);
                responseJSON = getFoodInfoByIdServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());

                break;
            case Config.GETUSERINFO://获取用户详细信息
                System.out.println("-----------------获取用户详细信息---------------------");
                UserInfoServlet userInfoServlet = new UserInfoServlet(object);
                responseJSON = userInfoServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.GETPUBLISHFOOD://获取用户已发布的菜品
                System.out.println("-----------------获取用户已发布的菜品---------------------");
                getUserPublishServlet getUserPublishServlet = new getUserPublishServlet(object);
                responseJSON = getUserPublishServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.GETFOODBYMATERIAL://通过已有的食材来推荐适合的菜品
                System.out.println("-----------------通过已有的食材来推荐适合的菜品---------------------");
                getFoodByMaterialServlet getFoodByMaterialServlet = new getFoodByMaterialServlet(object);
                responseJSON = getFoodByMaterialServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.GETUSERCOLLECTION://获取用户收藏的菜品
                System.out.println("-----------------获取用户收藏的菜品---------------------");
                getUserCollectionServlet getUserCollectionServlet = new getUserCollectionServlet(object);
                responseJSON = getUserCollectionServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.RECOMMENDFOOD://推荐食物给多个人
                System.out.println("-----------------推荐食物给多个人---------------------");
                recommendFoodServlet recommendFoodServlet = new recommendFoodServlet(object);
                responseJSON = recommendFoodServlet.getResponse(request, response);
                System.out.println(responseJSON.toString());
                break;
            case Config.RECOMMENDFOODFORTHISUSER://为该App的用户推荐菜品
                System.out.println("-----------------为该App的用户推荐菜品---------------------");
//                foodDAO foodDAO = new foodDAOImpl();
//                List list = new ArrayList();
//                list = foodDAO.getUserClickFoodId(object.getString("userId"));
//                recommendFoodForThisUserServlet recommendFoodForThisUserServlet = new recommendFoodForThisUserServlet(object, list);
//                responseJSON = recommendFoodForThisUserServlet.getResponse(request, response);

                getUserCollectionServlet userCollectionServlet = new getUserCollectionServlet(object);
                responseJSON = userCollectionServlet.getResponse(request, response);

                System.out.println(responseJSON.toString());
                break;
            case Config.UPDATEUSERIFO://更新用户信息
                System.out.println("-----------------更新用户信息---------------------");
                UserUpdateInfoServlet updateInfoServlet = new UserUpdateInfoServlet(object);
                responseJSON = updateInfoServlet.getResponse(request, response);
                System.out.println("返回数据:   " + responseJSON.toString());
                break;
        }

        /**
         * 向客户端发送一个带有json对象内容的响应
         */
        System.out.println("返回数据" + responseJSON.toString());
        response.getOutputStream().write(responseJSON.toString().getBytes("UTF-8"));
    }
}
