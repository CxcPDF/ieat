package recommend;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.ArrayList;
import java.util.List;

public class itemCF {

    /**
     * 基于itemCF的推荐算法
     * use the MYSQL database as the input for MAHOUT
     * 传来的参数为用户的userId和正在浏览的菜品foodId
     *
     * @param userId
     * @param foodId
     * @return
     */
    public static List getItem(String userId, String foodId) throws Exception {
        System.out.println("你好");
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "450642604";
        String url = "jdbc:mysql://localhost:3306/project_db";

        List foodList = new ArrayList();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url + "?useSSL=true");
        dataSource.setUser(user);
        dataSource.setPassword(password);


        JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "finalScore", "userId", "foodId", "score", "timeStamp");
        ReloadFromJDBCDataModel model = null;
        //利用ReloadFromJDBCDataModel包裹jdbcDataModel,可以把输入加入内存计算，加快计算速度。
        model = new ReloadFromJDBCDataModel(dataModel);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = null;
        itemSimilarity = new PearsonCorrelationSimilarity(model);
        AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(itemSimilarity);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity, candidateStrategy, candidateStrategy);
        //给用户ID为userId推荐10个与id为foodId相似的商品
        List<RecommendedItem> recommendedItemList = null;
        recommendedItemList = recommender.recommendedBecause(Long.parseLong(userId), Long.parseLong(foodId), 10);
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法,根据用户" + userId + "当前浏览的商品" + foodId + "，推荐10个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            foodList.add(recommendedItem.getItemID());
            System.out.println(recommendedItem);
        }
        return foodList;

    }


}
