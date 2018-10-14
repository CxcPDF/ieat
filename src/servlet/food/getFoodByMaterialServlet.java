package servlet.food;

import dao.foodDAO;
import dao.impl.foodDAOImpl;
import net.sf.json.JSONObject;
import servlet.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getFoodByMaterialServlet")
public class getFoodByMaterialServlet extends HttpServlet {

    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public getFoodByMaterialServlet(){

    }
    public getFoodByMaterialServlet(JSONObject jsonObject){
        this.jsonObject=jsonObject;
        responseJSON=new JSONObject();
    }

    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            this.doPost(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJSON;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String material=jsonObject.getString("material");
        CommonResponse res=new CommonResponse();
        foodDAO foodDAO=new foodDAOImpl();
        String data=foodDAO.getFoodByMaterial(material);
        responseJSON=res.setResult(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }
}
