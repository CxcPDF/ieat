package util;

import db.SQLHelper;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import config.*;

/**
 * 获取用户喜好信息
 */
public class GetUserPreference {


    SQLHelper sqlHelper=new SQLHelper();
    Connection connection=sqlHelper.getConnection();


    /**
     * 获取用户收藏的foodId
     * @param userId
     * @return
     * @throws SQLException
     */
    public List getFoodId(String userId) throws SQLException {
        List list=new ArrayList();
        String sql="select foodId from collection where userId="+userId;
        ResultSet resultSet = sqlHelper.queryRs(sql, sqlHelper, connection);
        if (resultSet==null){
            list.add((int)Math.random()*600+"");
            list.add((int)Math.random()*1000+"");
            list.add((int)Math.random()*300+"");
            list.add((int)Math.random()*400+"");
            list.add((int)Math.random()*200+"");
            return list;
        }
        while (resultSet.next()){
            list.add(resultSet.getString(Config.FOODID));
        }
        return list;
    }

}
