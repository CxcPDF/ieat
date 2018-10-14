package servlet;

import config.Config;
import net.sf.json.JSONObject;

//import net.sf.json.JSONObject;

public class CommonResponse {

    //    JSONObject jsonObject = new JSONObject();
//    JSONArray jsonArray=new JSONArray();
    JSONObject jsonData = new JSONObject();

    /**
     * 要发送的信息的格式为
     * {'response':[{'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
     * {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
     * {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'}]}
     * 将要发送的数据以json的格式进行封装
     *
     * @param data
     * @return
     */
    public JSONObject setResult(String data) {
//        jsonArray.addAll(Collections.singleton(data));

        StringBuilder str = new StringBuilder();
        str.append("[").append(data).append("]");

        jsonData.put(Config.RESPONSE, str + "");

//        System.out.println("json数据为"+str);

//        System.out.println("json数据为"+jsonData.toString());

        return jsonData;
    }

//    private String resCode;
//    private String resMsg;
//
//    private HashMap<String,String> property;
//    private ArrayList<HashMap<String,String>> list;
//
//    public CommonResponse(){
//        super();
//        resCode="";
//        resMsg="";
//        property=new HashMap<String,String>();
//        list=new ArrayList<HashMap<String, String>>();
//    }
//
//    public void setResult(String resCode,String resMsg){
//        this.resCode=resCode;
//        this.resMsg=resMsg;
//    }
//
//    public String getResCode() {
//        return resCode;
//    }
//
//    public void setResCode(String resCode) {
//        this.resCode = resCode;
//    }
//
//    public String getResMsg() {
//        return resMsg;
//    }
//
//    public void setResMsg(String resMsg) {
//        this.resMsg = resMsg;
//    }
//
//    public HashMap<String, String> getProperty() {
//        return property;
//    }
//
//    public void setProperty(HashMap<String, String> property) {
//        this.property = property;
//    }
//
//    public ArrayList<HashMap<String, String>> getList() {
//        return list;
//    }
//
//    public void setList(ArrayList<HashMap<String, String>> list) {
//        this.list = list;
//    }
//
//    public void addListItem(HashMap<String,String> map){
//        list.add(map);
//    }
}
