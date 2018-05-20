package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.GetData;
import database.SavaData;
import json.InfoToJson;

/**
 * Created by Westchennn on 17/3/23.
 */
public class PriceSelector extends HttpServlet {

    public PriceSelector() {super();}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String operation = request.getParameter("operation");
        String bookName = request.getParameter("bookName");

        if (operation.equals("getPrice")&&bookName!="") {
            String sql = "select * from info where bookName like '%" + bookName + "%' order by bookPrice + 0.0 asc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是result"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        //新更改的升序  没测试过！
        else if (operation.equals("getTmallPrice")&&bookName!="") {
            String sql = "select * from Tmall_info where Tmall_Name like '%" + bookName + "%' order by Tmall_Price asc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是Tmallresult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("getmiaosha")&&bookName!="") {
            String sql = "select * from info where miaoSha = '" + "秒杀" + "' and bookName like '%" + bookName + "%'";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.println("我是miaoSharesult"+result.toString());
                System.out.println("bookname:"+bookName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("getjinpai")&&bookName!="") {
            String sql = "select * from Tmall_info where Tmall_maiJiaJiBie = '" + "金牌卖家" + "' and Tmall_Name like '%" + bookName + "%'";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是jinPairesult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("getjingdongjiage")&&bookName!="") {
            String sql = "select * from info where bookName like '%" + bookName + "%' order by tuijian_JD_jiage + 0.0 desc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是tuijianjingdongJiaGeresult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("getjingdongzhiliang")&&bookName!="") {
            String sql = "select * from info where bookName like '%" + bookName + "%' order by tuijian_JD_zhiliang + 0.0 desc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是tuijianjingdongZhiLiangresult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("gettaobaojiage")&&bookName!="") {
            String sql = "select * from Tmall_info where Tmall_Name like '%" + bookName + "%' order by tuijian_TB_jiage + 0.0 desc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是tuijiantaobaoJiaGeresult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }
        else if (operation.equals("gettaobaozhiliang")&&bookName!="") {
            String sql = "select * from Tmall_info where Tmall_Name like '%" + bookName + "%' order by tuijian_TB_zhiliang + 0.0 desc";
            List<Map<String, Object>> result = new GetData().getPrice(sql);
            try {
                out.println(new InfoToJson().listToJson(result));
                System.out.print("我是tuijiantaobaoZhiLiangresult"+result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        }

        //可以考虑加淘宝里面只搜金牌卖家的
        //天猫里面只要热卖的两种operation处理
    }
}
