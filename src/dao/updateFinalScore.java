package dao;

import java.sql.SQLException;

//根据所有表生成FinalScore表，用于计算相似度Mahout库，字段userId，foodId，score，timeStamp
public interface updateFinalScore {
    public void updateFinalScore() throws SQLException;
}
