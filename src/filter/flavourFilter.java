package filter;

import dao.foodDAO;
import dao.impl.foodDAOImpl;
import db.SQLHelper;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 通过用户的口味来对推荐系统推荐的菜品进行筛选
 */
public class flavourFilter {
    SQLHelper helper=new SQLHelper();
    Connection connection=helper.getConnection();

//    public flavourFilter(int[] userFlavour){
//        this.userFlavour=userFlavour;
//    }


    /**
     * 根据foodId获取系统推荐的菜的口味信息
     * @param foodId
     * @return
     */
    private int[] getFoodFlavour(int foodId){
        int[] flavour=new int[5];
        String sql="select * from flavour where foodId="+foodId+";";
        try {
            ResultSet resultSet=helper.queryRs(sql,helper,connection);
            while (resultSet.next()){
                int isSour=resultSet.getInt("isSour");
                int isSweet=resultSet.getInt("isSweet");
                int isBitter=resultSet.getInt("isBitter");
                int isSpicy=resultSet.getInt("isSpicy");
                int isSalty=resultSet.getInt("isSalty");

                flavour[0]=isSour;
                flavour[1]=isSweet;
                flavour[2]=isBitter;
                flavour[3]=isSpicy;
                flavour[4]=isSalty;
            }

            resultSet.close();
            helper.close(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flavour;
    }

    /**
     * 筛选菜品
     * 参数：推荐菜品的id列表
     * 返回值：经过筛选后得到的菜品id
     * @param recommend_list
     * @return
     */
    public List<Integer> doFilterByFlavour(List recommend_list){
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<recommend_list.size();i++){
           int[] flavour=getFoodFlavour((Integer) recommend_list.get(i));
           if ((userFlavour[0]==0&&flavour[0]!=0)||
                   userFlavour[1]==0&&flavour[1]!=0||
                   (userFlavour[2]==0&&flavour[2]!=0)||
                   (userFlavour[3]==0&&flavour[3]!=0)||
                   (userFlavour[4]==0&&flavour[4]!=0)){
               list.remove(i);
           }
        }
        return list;
    }


    public List<Integer> doFilterByAvoidFood(List recommend_food,String condition){

        for (int i=0;i<recommend_food.size();i++){
            JSONObject json=getFoodAvoid((String) recommend_food.get(i));
            if (json.getInt(condition)>0){//如果对应的忌口在菜中出现，则去除这道菜
                recommend_food.remove(i);
            }
        }
        return recommend_food;
    }

    public JSONObject getFoodAvoid(String foodId){
        SQLHelper helper=new SQLHelper();
        JSONObject json=new JSONObject();
        String sql="select isSeafood,isVege,isPork from food where foodId="+foodId+";";
        try {
            ResultSet set=helper.queryRs(sql,helper,connection);
            while (set.next()){
                json.put("isSeafood",set.getInt("isSeafood"));
                json.put("isVege",set.getInt("isVege"));
                json.put("isPork",set.getInt("isPork"));
            }
            set.close();
            helper.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return json;
    }

    private int[] userFlavour=new int[4];//当前用户的口味，酸甜苦辣咸，若为0则表示该用户不吃该类菜

}
