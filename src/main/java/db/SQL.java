//package db;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SQLHelper {
//
//    private Connection conn = null;
//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//
//    /**
//     * 数据查询
//     * @paramsql语句
//     * @return 返回结果集List<Object>
//     */
//    public List<Object> query(String sql) {
//        if(sql.equals("") || sql == null){
//            return null;
//        }
//        List<Object> list = new ArrayList<Object>();
//        try {
//            conn = C3P0ConnentionProvider.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            ResultSetMetaData rsmd = rs.getMetaData();
//            // 可以得到有多少列
//            int columnNum = rsmd.getColumnCount();
//            // 将数据封装到list中
//            while (rs.next()) {
//                Object[] objects = new Object[columnNum];
//                for (int i = 0; i < objects.length; i++) {
//                    objects[i] = rs.getObject(i + 1);
//                }
//                list.add(objects);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }finally {
//            this.close();
//        }
//        return list;
//    }
//
//
//
//    /**
//     * 数据查询
//     * @paramsql语句
//     * @return 返回int 查询到的数目，0，则不存在
//     */
//    public int queryCount(String sql) throws SQLException {
//        if(sql.equals("") || sql == null){
//            return 0;
//        }
//        int countNum=0;
//        try {
//            conn = C3P0ConnentionProvider.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            // 将数据封装到list中
//            while (rs.next()) {
//                countNum++;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }finally {
//            this.close();
//        }
//        return countNum;
//    }
//
//
//
//
//    /**
//     * 数据查询
//     * @paramsql语句
//     * @return 返回结果集ResultSet
//     */
//    public ResultSet queryRs(String sql) throws SQLException {
//        if(sql.equals("") || sql == null){
//            return null;
//        }
//        try {
//            conn = C3P0ConnentionProvider.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return rs;
//    }
//    /**
//     * 执行更新语句
//     * @paramsql语句
//     * @return boolean 成功返回rews==1
//     */
//    public int update(String sql) {
//        System.out.println(sql);
//        int rows=0;
////        if(sql.equals("") || sql == null){
////            return 0;
////        }
//        try {
//            conn = C3P0ConnentionProvider.getConnection();
//            ps = conn.prepareStatement(sql);
//            rows = ps.executeUpdate();
//     //       System.out.println("rows"+rows);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }finally {
//            this.close();
//        }
//        return rows;
//    }
//    /**
//     * 关闭资源
//     */
//    public void close() {
//        try {
//            if (conn!=null){
//                conn.close();
//                conn=null;
//            }
//        }catch (Exception e){
//            System.out.println("关闭资源时发生异常");
//            e.printStackTrace();
//        }
//    }
//
//}
