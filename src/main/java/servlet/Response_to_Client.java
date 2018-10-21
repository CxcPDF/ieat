package servlet;//package servlet;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//
///**
// * 将要发送给客户端的数据进行封装
// */
//public class Response_to_Client {
//
//    JSONObject jsonObject = new JSONObject();
//    JSONArray jsonArray=new JSONArray();
//    JSONObject json=new JSONObject();
//
//    /**
//     * 要发送的信息的格式为
//     * {'data':[{'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
//     *          {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
//     *          {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'}]}
//     * 将要发送的数据以json的格式进行封装
//     * @param kvs
//     * @return
//     */
//    public JSONObject makeJSON(String... kvs) {
//        for (int i=0;i<kvs.length;i+=2) {
//            jsonObject.put(kvs[1], kvs[i + 1]);
//        }
//        jsonArray.add(jsonObject);
//
//        json.put("data",jsonObject);
//        return json;
//    }
//}
//
//
////package servlet;
////
////import java.util.ArrayList;
////import java.util.HashMap;
////
////public class CommonResponse {
////
////    private String resCode;
////    private String resMsg;
////
////    private HashMap<String,String> property;
////    private ArrayList<HashMap<String,String>> list;
////
////    public CommonResponse(){
////        super();
////        resCode="";
////        resMsg="";
////        property=new HashMap<String,String>();
////        list=new ArrayList<HashMap<String, String>>();
////    }
////
////    public void setResult(String resCode,String resMsg){
////        this.resCode=resCode;
////        this.resMsg=resMsg;
////    }
////
////    public String getResCode() {
////        return resCode;
////    }
////
////    public void setResCode(String resCode) {
////        this.resCode = resCode;
////    }
////
////    public String getResMsg() {
////        return resMsg;
////    }
////
////    public void setResMsg(String resMsg) {
////        this.resMsg = resMsg;
////    }
////
////    public HashMap<String, String> getProperty() {
////        return property;
////    }
////
////    public void setProperty(HashMap<String, String> property) {
////        this.property = property;
////    }
////
////    public ArrayList<HashMap<String, String>> getList() {
////        return list;
////    }
////
////    public void setList(ArrayList<HashMap<String, String>> list) {
////        this.list = list;
////    }
////
////    public void addListItem(HashMap<String,String> map){
////        list.add(map);
////    }
////}
