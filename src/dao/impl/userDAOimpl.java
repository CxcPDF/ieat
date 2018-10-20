package dao.impl;

import bean.user;
import config.Config;
import dao.userDAO;
import db.SQLHelper;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//import db.DBManager;

public class userDAOimpl implements userDAO {
    SQLHelper sqlHelper = new SQLHelper();
    Connection connection = sqlHelper.getConnection();


    /**
     * 用户注册
     *
     * @param user
     * @param type
     * @return
     */
    @Override
    public boolean addUser(user user, String type) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
        String sql;
        switch (type) {
            case Config.TEL://通过电话号码注册
                sql = "insert into user(account,psd) values(\'" +
                        user.getUserTel() + "\',\'" + user.oauthAccessToken + "\');";
                return (sqlHelper.update(sql, sqlHelper, connection) == 1);
            case Config.WEICHAT://通过微信注册
                sql = "insert into user(account,psd) values(\'" +
                        user.getUserWeiChat() + "\',\'" + user.oauthAccessToken + "\');";
                return (sqlHelper.update(sql, sqlHelper, connection) == 1);
            case Config.QQ://通过QQ注册
                sql = "insert into user(account,psd) values(\'" +
                        user.getUserQQ() + "\',\'" + user.oauthAccessToken + "\');";
                return (sqlHelper.update(sql, sqlHelper, connection) == 1);
        }

        return false;
    }

    @Override
    public int update(user user, String sql) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
        return sqlHelper.update(sql, sqlHelper, connection);
    }


    /**
     * 返回对应账号的密码
     *
     * @param account
     * @param type
     * @return
     * @throws SQLException
     */
    @Override
    public String getAccess(String account, String type) throws SQLException {
//        DBManager dbManager=new DBManager();
//        Statement statement=dbManager.getConnect();
        String sql = "select psd from user where account='" +
                account + "';";
        ResultSet resultSet = sqlHelper.queryRs(sql, sqlHelper, connection);
        String oauthAccessToken = null;
        if (resultSet == null) {
            return oauthAccessToken;
        }
        while (resultSet.next()) {
            oauthAccessToken = resultSet.getString("psd");
        }


        resultSet.close();
        sqlHelper.close(connection);


        return oauthAccessToken;
    }


    /**
     * 获取一个用户的详细信息
     *
     * @param userId
     * @return
     */
    @Override
    public JSONObject getUserInfo(String userId) {
        JSONObject json = new JSONObject();

        //获取用户的基本信息如昵称，身高，体重，年龄，性别等
        String sql = "select nickName,height,weight,age,sex,account,suit_people,avoid_food from user where userId=" + userId + ";";
        try {
            ResultSet resultSet = sqlHelper.queryRs(sql, sqlHelper, connection);
//            if (resultSet==null){
//                return null;
//            }
            while (resultSet.next()) {
                json.put(Config.NICKNAME, resultSet.getString(Config.NICKNAME));
                json.put(Config.HEIGHT, resultSet.getInt(Config.HEIGHT));
                json.put(Config.WEIGHT, resultSet.getInt(Config.WEIGHT));
                json.put(Config.AGE, resultSet.getInt(Config.AGE));
                json.put(Config.SEX, resultSet.getString(Config.SEX));
                json.put(Config.ACCOUNT, resultSet.getString(Config.ACCOUNT));
                json.put(Config.SUITPEOPLE, resultSet.getString(Config.SUITPEOPLE));
                json.put(Config.AVOIDFOOD, resultSet.getString(Config.AVOIDFOOD));

            }

            resultSet.close();
            sqlHelper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return json;
    }


    /**
     * 获取用户收藏的菜品
     * 消息的返回格式为
     * "{'foodId':'id','foodName':'name','foodStar':'star','material':'material','image':'image'},
     * {'foodId':'id','foodName':'name','foodStar':'star','material':'material','image':'image'}"
     *
     * @param userId
     * @return
     */
    @Override
    public String getUserCollection(String userId) {

        StringBuilder sb = new StringBuilder();
//        JSONObject data=new JSONObject();
        String sql = "select foodId,foodName,foodStar,material,image from food where foodId in (select foodId from collection where userId=" + userId + ");";
        try {
            ResultSet set = sqlHelper.queryRs(sql, sqlHelper, connection);
            while (set.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Config.FOODID, set.getInt("foodId"));
                jsonObject.put(Config.FOODNAME, set.getString("foodName"));
                jsonObject.put(Config.FOODSTAR, set.getString("foodStar"));
                jsonObject.put(Config.FOODMATERIAL, set.getString("material"));
                jsonObject.put(Config.IMAGEURL, set.getString("image"));
//                data.put("food",jsonObject);
                sb.append(jsonObject).append(",");
            }

            set.close();
            sqlHelper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = sb.substring(0, sb.length() - 1);
        return str;
    }

    /**
     * 获取用户已经发布的菜品
     * 消息的返回格式为
     * "{'foodId':'id','foodName':'name','foodStar':'star','material':'material','image':'image','step':'step'},
     * {'foodId':'id','foodName':'name','foodStar':'star','material':'material','image':'image','step':'step'}"
     *
     * @param userId
     * @return
     */
    @Override
    public String getPublishFood(String userId) {
//        JSONObject data=new JSONObject();
        StringBuilder sb = new StringBuilder();
        String sql = "select foodId,foodName,foodStar,material,image,step from food where foodId in (select foodId from publish where userId=" + userId + ");";
        try {
            ResultSet set = sqlHelper.queryRs(sql, sqlHelper, connection);
            while (set.next()) {
                JSONObject json = new JSONObject();
                json.put(Config.FOODID, set.getInt("foodId"));
                json.put(Config.FOODNAME, set.getString("foodName"));
                json.put(Config.FOODSTAR, set.getString("foodStar"));
                json.put(Config.FOODMATERIAL, set.getString("material"));
                json.put(Config.IMAGEURL, set.getString("image"));
                json.put(Config.STEP, set.getString("step"));
                sb.append(json).append(",");
//                data.put("food",json);
            }

            set.close();
            sqlHelper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = sb.substring(0, sb.length() - 1);
        return str;
    }

    /**
     * 获取一道菜的详细信息
     *
     * @param foodId
     * @return
     */
    @Override
    public String getFoodInfoById(String foodId) {
//        StringBuilder sb=new StringBuilder();
        String sql = "select foodId,foodName,foodStar,material,image,step from food where foodId=" + foodId + ";";
        JSONObject json = new JSONObject();
        try {
            ResultSet set = sqlHelper.queryRs(sql, sqlHelper, connection);
            while (set.next()) {
//                json.put(Config.FOODID,set.getInt("foodId"));
                json.put(Config.FOODID, set.getInt("foodId"));
                json.put(Config.FOODNAME, set.getString("foodName"));
                json.put(Config.FOODSTAR, set.getString("foodStar"));
                json.put(Config.FOODMATERIAL, set.getString("material"));
                json.put(Config.IMAGEURL, set.getString("image"));
                json.put(Config.STEP, set.getString("step"));
//                sb.append(json).append(",");
//                data.put("food",json);
            }

            set.close();
            sqlHelper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String str=sb.substring(0,sb.length()-1);
        return json.toString();
    }

    @Override
    public String getUserId(String account) {
        String userId = null;
        String sql = "select userId from user where account='" + account + "';";
        try {
            ResultSet set = sqlHelper.queryRs(sql, sqlHelper, connection);
            while (set.next()) {
                userId = set.getString("userId");
            }

            set.close();
            sqlHelper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public int[] getFlavourById(String userId) {


        return new int[0];
    }

    @Override
    public boolean updateUserInfo(JSONObject jsonObject) {

        String userId = jsonObject.getString(Config.USERID);
        String height = jsonObject.getString(Config.HEIGHT);
        String weight = jsonObject.getString(Config.WEIGHT);
        String age = jsonObject.getString(Config.AGE);
        String sex = jsonObject.getString(Config.SEX);
        String suit_people = jsonObject.getString(Config.SUITPEOPLE);//特殊人群
        String avoid_food = jsonObject.getString(Config.AVOIDFOOD);//忌口食物
        String sql="update user set height="+height+" and weight="+weight+" " +
                "age="+age+" sex="+sex+" suit_people="+suit_people+" avoid_food="+avoid_food+" " +
                "where userId="+userId+";";

        int row=0;
        try {
            row=sqlHelper.update(sql, sqlHelper, connection);
            return true;

        }catch (Exception e){
            System.out.println(e.getCause());
        }
        return false;
    }


//    @Override
//    public int getUserId() {
//
//        return 0;
//    }
//
//    @Override
//    public boolean checkTel_and_QQ(String Tel, String QQ) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
//        String sql = "insert into user(userTel,userQQ) values (\'" + Tel + "\',\'" + QQ + "\');";
//        return (dbManager.update(sql, statement) == 1);
//    }
//
//    @Override
//    public String getPsd(String usertName) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
//        String sql = "select userPassword from user where user";
//        return null;
//    }

}
