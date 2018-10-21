package dao;
import java.util.*;

public interface foodDAO {
    public String getFoodByMaterial(String data);//根据用户输入的关键字来进行搜索

    public List getSuitPeopleFood(String people);//获取适合不同用户吃的菜

    public List getUserClickFoodId(String userId);//获取用户在一定时间内点击的食物的foodId
}
