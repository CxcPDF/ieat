package dao.impl;

import bean.actionPre;
import dao.actionPreDAO;
import db.SQLHelper;

import java.sql.Connection;


public class actionPreDAOimpl implements actionPreDAO {
    SQLHelper helper=new SQLHelper();
    Connection connection=helper.getConnection();


    @Override
    public boolean addActionPre(actionPre actionPre) {
//        DBManager dbManager=new DBManager();
//        Statement statement=dbManager.getConnect();
        String sql;
        sql="insert into actionpre(userId,foodId,isCollection,clickTime,star) values(" +
                actionPre.getUserId()+","+ actionPre.getFoodId()+","+ actionPre.isCollection()+",\'"+ actionPre.getClickTime()+"\',"+actionPre.getStar()+");";
//        sql="insert into actionPre(userId,foodId,isCollection,clickTime,star) \n" +
//                "values(13,333,true,\"2018-04-18\",2);";

        int row=helper.update(sql,helper,connection);
        return (row==1);
    }
}

