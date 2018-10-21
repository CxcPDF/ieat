package dao;

import bean.user;
import net.sf.json.JSONObject;
import sun.net.idn.Punycode;

import java.sql.SQLException;

public interface userDAO {
    public boolean addUser(user user, String type);//根据用户选择登录方式的不同来添加用户
    public int update(user user, String sql);//更新用户信息
    public String getAccess(String key, String type) throws SQLException;//由输入的电话号码或者QQ号来获取对应的密码

    public JSONObject getUserInfo(String userId);//通过userId来获取用户的基本信息

    public String getUserCollection(String userId);//通过userId来获取用户收藏的菜品

    public String getPublishFood(String userId);//通过userId来获取用户发布的菜品

    public String getFoodInfoById(String foodId);//通过foodId来获取菜品信息

    public String getUserId(String account);//通过account来获取userId


    public int[] getFlavourById(String userId);//通过userId来获取用户的口味信息

    public boolean updateUserInfo(JSONObject json);//更新用户信息

}
