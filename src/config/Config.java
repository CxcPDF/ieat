package config;

public class Config {
    /*
        user 表
    */
    public static final String TEL="tel";
    public static final String WEICHAT="weichat";
    public static final String QQ="qq";


    public static final String ACCOUNT="account";
    public static final String PASSWORD="password";

    public static final String REQUEST_TYPE="request_type";
    public static final String REGISTER="register";
    public static final String LOGIN="login";
    public static final String FOOD_CLICK="food_click";


    public static final String UPDATE_ACTION="update_action";

    /*
     * actionPre 记录用户行为的表
     * */
    public static final String USERID="userId";
    public static final String FOODID="foodId";
    public static final String ISCOLLECTION="isCollection";
    public static final String CLICKTIME="clickTime";
    public static final String STAR="star";

//    /*
//     * 连接数据库信息
//     * */
//    public static final String URL="jdbc:mysql://localhost:3306/project_db";
//    public static final String USER="root";
//    public static final String PSD="450642604";
//    public static final String DRIVER="com.mysql.jdbc.Driver";

    public  static final String DRIVER="com.mysql.jdbc.Driver";
    public  static final String USER="root";
    public  static final String PSD="450642604";
    public  static final String URL="jdbc:mysql://localhost:3306/project_db?useSSL=true";

    /**
     * 发送给客户端的key值
     */
    public static final String FOODNAME="foodName";
    public static final String FOODMATERIAL="foodMaterial";
    public static final String IMAGEURL="imageUrl";

    public static final String NOTICE="notice";
    public static final String SUCCESS_TO_LOGIN="success_to_login";
    public static final String FAIL_TO_LOGIN="fail_to_login";
    public static final String SUCCESS_TO_REGISTER="success_to_register";
    public static final String FAIL_TO_REGISTER="fail_to_register";




    public static final String AVOIDFOOD="avoid_food";
    public static final String SUITPEOPLE="suit_people";




    public static final String NICKNAME="nickName";
    public static final String HEIGHT="height";
    public static final String WEIGHT="weight";
    public static final String AGE="age";
    public static final String SEX="sex";

    public static final String RESPONSE="response";
    public static final String FOODSTAR="foodStar";
    public static final String STEP="step";


    public static final String GETFOODINFOBYID="getFoodInfoById";
    public static final String GETUSERINFO="getUserInfo";
    public static final String GETPUBLISHFOOD="getPublishFood";
    public static final String GETFOODBYMATERIAL="getFoodByMaterial";
    public static final String RECOMMENDFOOD="recommendFood";
    public static final String GETUSERCOLLECTION="getUserCollection";
    public static final String GETUSERIDBYACCOUNT="getUserIdByAccount";
    public static final String RECOMMENDFOODFORTHISUSER="recommendFoodForThisUser";



    public static final String STATUSCODE="status_code";
    public static final int FAIL=404;
    public static final int SUCCESS=200;




}
