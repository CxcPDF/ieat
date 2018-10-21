package dao.impl;

import dao.updateActionDAO;
import db.SQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class updateActionDAOimpl implements updateActionDAO {
    SQLHelper sqlHelper = new SQLHelper();
    Connection connection=sqlHelper.getConnection();

    final Timer timer = new Timer();
    ResultSet rs;
    ResultSet rs1;
    ResultSet rs2;
    public void updateAction() throws SQLException {
        String sql = "SELECT * FROM actionpre;";
        rs = sqlHelper.queryRs(sql,sqlHelper,connection);
        System.out.println("updateActionDAOimpl");
        timer.schedule(task, 0, 10);// 2秒一次
    }

    TimerTask task = new TimerTask() {
        private int count=0;

        @Override
        public void run() {
            try {
                if(rs.next()){
                    if(rs.getObject("userId")==null||rs.getObject("foodId")==null||rs.getObject("isCollection")==null||rs.getObject("clickTime")==null||rs.getObject("star")==null){

                    }else {
                        String userId=  rs.getString("userId");
                        String foodId=rs.getString("foodId");
                        String isCollection=rs.getString("isCollection");
                        String clickTime=rs.getString("clickTime");
                        String star=rs.getString("star");
                        int isSour=0;
                        int isSweet=0;
                        int isBitter=0;
                        int isSpicy=0;
                        int isSalty=0;
//                sql1查在action表中是否存在用户userId
                        String sql1="SELECT * FROM action " +
                                "where userId="+userId+";";

                        //在表flavour中查寻foodId的食物的酸甜苦辣
                        String sql2="SELECT * FROM flavour " +
                                "where foodId="+foodId+";";
                        if(sqlHelper.queryCount(sql2,sqlHelper,connection)==1){
                            SQLHelper sqlHelper1=new SQLHelper();
                            rs1 =sqlHelper1.queryRs(sql2,sqlHelper,connection);
                            while (rs1.next()){
                                isBitter=rs1.getInt("isBitter");
                                isSalty=rs1.getInt("isSalty");
                                isSour=rs1.getInt("isSour");
                                isSpicy=rs1.getInt("isSpicy");
                                isSweet=rs1.getInt("isSweet");
                            }
                            rs1.close();
                            sqlHelper1.close(connection);
                        }
                        if(sqlHelper.queryCount(sql1,sqlHelper,connection)==0){
                            //若数据库中无该用户信息，插入口味信息
                            String sql3="INSERT INTO action (userId, sour, sweet,bitter,spicy,salty)\n" +
                                    "    VALUES ("+userId+","+isSour+","+isSweet+","+isBitter+","+isSpicy+","+isSalty+");";
                            sqlHelper.update(sql3,sqlHelper,connection);
                        }
                        else {
                            //若数据库中有该用户信息，更新信息action表
                            String sql4="SELECT * FROM project_db_sql.action\n" +
                                    "where userId="+userId+";";
                            SQLHelper sqlHelper2=new SQLHelper();
                            rs2 =sqlHelper2.queryRs(sql4,sqlHelper,connection);

                            int isBitterOld=0;
                            int isSaltyOld=0;
                            int isSourOld=0;
                            int isSpicyOld=0;
                            int isSweetOld=0;
                            boolean isNext=false;
                            while (rs2.next()){
                                isNext=true;
                                isBitterOld=rs2.getInt("bitter")+isBitter;
                                isSaltyOld=rs2.getInt("salty")+isSalty;
                                isSourOld=rs2.getInt("sour")+isSour;
                                isSpicyOld=rs2.getInt("spicy")+isSpicy;
                                isSweetOld=rs2.getInt("sweet")+isSweet;

                            }
                            String sql5="UPDATE action SET sour = "+isSourOld+", sweet = "+isSweetOld+",bitter="+isBitterOld+",spicy="+isSpicyOld+",salty="+isSaltyOld+"\n" +
                                    "WHERE userId = "+userId+";";
                            rs2.close();
                            sqlHelper2.close(connection);
                            if(isNext) {
                                sqlHelper.update(sql5,sqlHelper,connection);
                            }
                         //   System.out.println(1000);
                        }

                    }
                }
                else {
                    timer.cancel();// 停止定时器


                    rs.close();
                    sqlHelper.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.count++;
            System.out.println(count);
        }
    };
}
