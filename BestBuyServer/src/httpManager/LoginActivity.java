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

/*
import org.json.JSONException;
*/

import bean.BaseUser;
import database.GetData;
import database.SavaData;
import json.InfoToJson;
/**
 * Created by Westchennn on 17/3/18.
 */
public class LoginActivity extends HttpServlet{
    public LoginActivity() {super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //验证用户的密码正确与否
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String operation = request.getParameter("operation");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");



//改了operation的指令 为Login
        if (operation.equals("Login")) {
            List<Map<String, Object>> list = new GetData().getUser();
            for (int i = 0; i < list.size(); i++) {

                if (userName.equals(list.get(i).get("userName")) && password.equals(list.get(i).get("password"))) {
                    try {
                        out.println(new InfoToJson().mapToJson(list.get(i)));
                        System.out.println(list.get(i).get("name") + "上线了");
                        break;
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            out.println("-1");
        } else if (operation.equals("Enroll_BaseUser")) {
            String sql = "insert into user_data values(?,?,?,?,?,?,?,?,?,?,?)";
            String userName_enroll = request.getParameter("userName");
            String password_enroll = request.getParameter("password");
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            String age = request.getParameter("age");
            String head = request.getParameter("head");
            String personalID = request.getParameter("personalID");
            String phone = request.getParameter("phone");
            String type = request.getParameter("type");
            String realName = request.getParameter("realName");
            Object[] params = {String.valueOf(UUID.randomUUID()), userName_enroll, password_enroll, name, sex, age,
                    head, personalID, phone, type, realName};
            int lineNum = new SavaData().savaInfo(sql, params);

            System.out.println("新用户" + name + "注册加入系统了");

            out.println("1");
        } else if (operation.equals("Enroll_had")) {

            String Enroll_userName = request.getParameter("userName");
            String Enroll_password = request.getParameter("password");

            List<Map<String, Object>> list = new GetData().getUser();
            for (int i = 0; i < list.size(); i++) {

                if (Enroll_userName.equals(list.get(i).get("userName"))
                        && Enroll_password.equals(list.get(i).get("password"))) {

                    out.println("用户存在");
                    break;
                }
                out.println("用户不存在");
            }
        }
    }
}
