package json;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.BaseUser;
import bean.Information;

/**
 * Created by Westchennn on 17/3/18.
 */
public class InfoToJson {
    public InfoToJson() {
    }

    //一般List<Map<String,Object>>集合转换成JSONArray字符串
    public String listToJson(List<Map<String,Object>> list) throws JSONException {

        JSONArray jsonList = new JSONArray();

        if (list == null)
            System.out.println("数组为空");
        else {
//			System.out.println("准备进入循环");
            for (int i = 0; i < list.size(); i++) {
//				System.out.println("进入循环" + i);
                JSONObject json = new JSONObject();

                for(Map.Entry<String, Object> entry : list.get(i).entrySet()){
//					System.out.println("进入子循环");
                    json.put(entry.getKey(), entry.getValue());
                }

                jsonList.put(json);
            }
        }
        if (jsonList.toString() != null)
            return jsonList.toString();
        else{
            System.out.println("InfoToJson().listToJson方法最后结果为空");
            return null;
        }
    }

    //一般List<Map<String,Object>>集合转换成JSONObejct字符串
    public String mapToJson(Map<String,Object> map) throws JSONException {

        if (map == null)
            System.out.println("InfoToJson:数组为空");
        else {
            JSONObject json = new JSONObject();

            for(Map.Entry<String, Object> entry : map.entrySet())
                json.put(entry.getKey(), entry.getValue());

            return json.toString();
        }
        return null;
    }

    public String baseUserToJson(BaseUser user) throws JSONException {

        if (user == null)
            System.out.println("内容为空");
        else {
            JSONObject json = new JSONObject();

            json.put("id", user.getId());
            json.put("head", user.getHead());
            json.put("name", user.getName());
            json.put("userName", user.getUserName());
            json.put("password", user.getPassword());
            json.put("sex", user.getSex());
            json.put("personalID", user.getIDCard());
            json.put("phone", user.getTelephone());

            return json.toString();
        }
        return null;
    }
}
