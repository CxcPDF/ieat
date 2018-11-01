package dao.impl;

import config.Config;
import dao.foodDAO;
import db.SQLHelper;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * 根据用户输入的关键字来进行搜索,关键字之间用“,”分隔（英文逗号）
 */
public class foodDAOImpl implements foodDAO {

    @Override
    public String getFoodByMaterial(String data) {
        SQLHelper helper = new SQLHelper();
        Connection connection=helper.getConnection();

        StringBuilder sb = new StringBuilder();

        String[] material = data.split(",");
        String sql = "select foodId,foodName,material,step from food where material like '%" + material[0] + "%'";
        for (int i = 0; i < material.length; i++) {
            sql += " or material like '%" + material[i] + "%'";
        }

        JSONObject jsonObject = new JSONObject();
        try {
            ResultSet set = helper.queryRs(sql,helper,connection);
            while (set.next()) {
                jsonObject.put(Config.FOODID, set.getString("foodId"));
                jsonObject.put(Config.FOODNAME, set.getString("foodName"));
                jsonObject.put(Config.FOODMATERIAL, set.getString("material"));
                jsonObject.put(Config.STEP, set.getString("step"));
                sb.append(jsonObject.toString()).append(",");
            }

//            set.close();
            helper.close(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String str = sb.substring(0, sb.length() - 1);


//        List list=new ArrayList();

        return str;
    }


    /**
     * 推荐出适合不同人群的菜品
     *
     * @param people
     * @return
     */
    @Override
    public List getSuitPeopleFood(String people) {
        SQLHelper helper = new SQLHelper();
        Connection connection=helper.getConnection();

        List list=new ArrayList();
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
        String[] human = people.split(",");
        String sql = "SELECT foodId,foodName,material,image FROM food WHERE foodId IN (SELECT foodId FROM suitpeople WHERE 1=1";
        for (int i = 0; i < human.length; i++) {
            sql += " or " + human[i] + "=1";
        }
        sql += ");";

//        JSONObject json = new JSONObject();

        try {
            ResultSet set = helper.queryRs(sql,helper,connection);
            while (set.next()) {
//                json.put(Config.FOODID, set.getString("foodId"));
//                json.put(Config.FOODNAME, set.getString("foodName"));
//                json.put(Config.FOODMATERIAL, set.getString("material"));
//                json.put(Config.IMAGEURL, set.getString("image"));
//                sb.append(json).append(",");
                list.add(set.getInt("foodId"));
            }

//            set.close();
            helper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String str = sb.substring(0, sb.length() - 1);
        return list;
    }

    @Override
    public List getUserClickFoodId(String userId) {
        SQLHelper helper = new SQLHelper();
        Connection connection=helper.getConnection();

        List list=new ArrayList();
        long nowTime=new Date().getTime();
        //找出近两个月内该用户的点击记录
//        String sql="select distinct foodId from actionpre where userId="+userId+" and timeStamp>="+(nowTime-60*24*60*60*1000)+";";
        String sql="select distinct foodId from actionpre where userId="+userId+";";
        try {
            ResultSet set=helper.queryRs(sql,helper,connection);
            while (set.next()){
                list.add(set.getInt("foodId"));
            }

//            set.close();
            helper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List recommendFood(String sql) {
        SQLHelper helper = new SQLHelper();
        Connection connection=helper.getConnection();
        List list=new ArrayList();
        try {
            ResultSet set=helper.queryRs(sql,helper,connection);
            while (set.next()){
                list.add(set.getInt("foodId"));
            }
            helper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
