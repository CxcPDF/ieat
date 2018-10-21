//package db;
//
//import com.mchange.v2.c3p0.DataSources;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * c3p0连接池管理类
// * @author ICE
// *
// */
//public class C3P0ConnentionProvider {
//
//    private static final String JDBC_DRIVER = "driverClass";
//    private static final String JDBC_URL = "jdbcUrl";
//
//    private static DataSource ds;
//    /**
//     * 初始化连接池代码块
//     */
//    static{
//        initDBSource();
//    }
//
//    /**
//     * 初始化c3p0连接池
//     */
//    private static final void initDBSource(){
//        Properties c3p0Pro = new Properties();
//        try {
//            //加载配置文件
//            c3p0Pro.load(C3P0ConnentionProvider.class.getResourceAsStream("c3p0.properties"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String driverClass = c3p0Pro.getProperty(JDBC_DRIVER);
//        if(driverClass != null){
//            try {
//                //加载驱动类
//                System.out.println(driverClass);
////                Class.forName(driverClass);
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                System.out.println("error!!!");
//                e.printStackTrace();
//                e.getMessage();
//            }
//
//        }
//
//        Properties jdbcpropes = new Properties();
//        Properties c3propes = new Properties();
//        for(Object key:c3p0Pro.keySet()){
//            String skey = (String)key;
//            if(skey.startsWith("c3p0.")){
//                c3propes.put(skey, c3p0Pro.getProperty(skey));
//            }else{
//                jdbcpropes.put(skey, c3p0Pro.getProperty(skey));
//            }
//        }
//
//        try {
//            //建立连接池
//            DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL),jdbcpropes);
//            ds = DataSources.pooledDataSource(unPooled,c3propes);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取数据库连接对象
//     * @return 数据连接对象
//     * @throws SQLException
//     */
//    public static synchronized Connection getConnection() throws SQLException{
//        final Connection conn = ds.getConnection();
//        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        return conn;
//    }
//}