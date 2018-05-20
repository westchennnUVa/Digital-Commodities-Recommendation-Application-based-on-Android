package jiemian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.model.Product;
import com.example.westchen.priceselector.PriceSelectorShow;
import com.example.westchen.priceselector.PriceSelectorShowTuiJian;
import com.example.westchen.priceselector.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;


public class PersonalActivityFragment extends Fragment {

    private String s;

    private Button priceSelectorConfirmButton;
    private Button priceSelectorConfirmButtonTmall;
    private Button jingdongmiaosha;
    private Button jinpaimaijia;
    private Button jingdongtuijianjiage;
    private Button taobaotuijianjiage;
    private Button jingdongtuijianzhiliang;
    private Button taobaotuijianzhiliang;
    private EditText priceSelectorUserInput;

    private JSONArray array;

    // 产品列表
    private ArrayList<Product> proList;

    private ArrayList bundlelist;




    private Handler handler;
    private Handler handler2;
    private Handler handler3, handler4,handler5,handler6,handler7,handler8;


    public static final int HANDLER_LOGIN_SUCCESS=1;
    public static final int HANDLER_LOGIN_ERROR=2;

    //Map<String,String> list=new HashMap<String,String>();

    Map<String,String> list=new LinkedHashMap<String,String>();
    Map<String,String> listTmall=new LinkedHashMap<String,String>();
    Map<String,String> listmiaosha=new LinkedHashMap<String,String>();
    Map<String,String> listjinpai=new LinkedHashMap<String,String>();
    Map<String,String> listjingdongzhiliang=new LinkedHashMap<String,String>();
    Map<String,String> listjingdongjiage=new LinkedHashMap<String,String>();
    Map<String,String> listtaobaozhiliang=new LinkedHashMap<String,String>();
    Map<String,String> listtaobaojiage=new LinkedHashMap<String,String>();


    //private SimpleAdapter simple;
    private List<HashMap<String, Object>> simpleadapterlistTest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_priceselector, null);

        Bundle bundle = getArguments();
        s = bundle.getString("id");

        //setViews();
        priceSelectorConfirmButton = (Button)view.findViewById(R.id.userInputConfirmButton);
        priceSelectorConfirmButtonTmall = (Button)view.findViewById(R.id.userInputConfirmButtonTmall);
        jingdongmiaosha = (Button)view.findViewById(R.id.jingdongmiaosha);
        jinpaimaijia = (Button)view.findViewById(R.id.jinpaimaijia);
        jingdongtuijianjiage = (Button)view.findViewById(R.id.jingdongtuijianjiage);
        taobaotuijianjiage = (Button)view.findViewById(R.id.taobaotuijianjiage);
        jingdongtuijianzhiliang = (Button)view.findViewById(R.id.jingdongtuijianzhiliang);
        taobaotuijianzhiliang = (Button)view.findViewById(R.id.taobaotuijinzhiliang);

        priceSelectorUserInput = (EditText)view.findViewById(R.id.userInput);
        //jingDongList = (ListView)findViewById(R.id.jingDongProductList);
        // 初始化查询产品列表
        proList = new ArrayList<Product>();
        simpleadapterlistTest = new ArrayList<HashMap<String, Object>>();
        priceSelectorConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            usePriceSelector();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "京东查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "京东查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        priceSelectorConfirmButtonTmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            usePriceSelectorTmall();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler2=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "天猫查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "天猫查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        jingdongmiaosha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selectmiaosha();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler3=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "秒杀产品查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "秒杀产品查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        jinpaimaijia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selectjinpai();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler4=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "金牌卖家产品查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "金牌卖家产品查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        jingdongtuijianjiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selectjingdongjiage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler5=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "京东下按照价格推荐度推荐查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "京东下按照价格推荐度推荐查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        jingdongtuijianzhiliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selectjingdongzhiliang();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler6=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "京东下按照质量推荐度推荐查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "京东下按照质量推荐度推荐查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };


        taobaotuijianjiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selecttaobaojiage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler7=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "淘宝下按照价格推荐度推荐查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "淘宝下按照价格推荐度推荐查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        taobaotuijianzhiliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            selecttaobaozhiliang();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        handler8=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(getActivity(), "淘宝下按照质量推荐度推荐查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(getActivity(), "淘宝下按照质量推荐度推荐查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        return view;
    }

    private /*List<String>*/void usePriceSelector() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlistjingdong=new ArrayList<>();
        list.put("operation", "getPrice");
        list.put("bookName", priceSelectorUserInput.getText().toString());
        String result = new HttpUtil().post(CommonUrl.PriceSelector,list );
        Log.i("get price", result);
        if(!result.equals("-1")){
            JSONArray datelist = new JSONArray(result);
//            Log.i("get data", result.toString());
            System.out.print(datelist);
            Log.i("datalist",datelist.toString());
            array = datelist;


            int size = array.length();
            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShow.class);
            if(size > 0){
                for(int i=0;i<size;i++) {
                    //bookID[i] = array.get(i).toString();
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("bookName"));
                    hashmap.put("price", (String) object.get("bookPrice"));
                    hashmap.put("commentNum", (String) object.get("commentNum"));
                    hashmap.put("commentValue", (String) object.get("commentValue"));
                    simpleadapterlistjingdong.add(hashmap);
                }
                handler.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlistjingdong);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else {
            handler.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }


    }

    private void usePriceSelectorTmall() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlistTmall=new ArrayList<>();
        listTmall.put("operation", "getTmallPrice");
        listTmall.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listTmall);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall", datelistTmall.toString());


            int size = array.length();
            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShow.class);
            if(size > 0){
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("Tmall_Name"));
                    hashmap.put("price", (String) object.get("Tmall_Price"));
                    hashmap.put("Tmall_commentNum", (String) object.get("Tmall_commentNum"));
                    hashmap.put("Tmall_commentValue", (String) object.get("Tmall_commentValue"));
                    simpleadapterlistTmall.add(hashmap);
                }
                handler2.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlistTmall);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler2.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selectmiaosha() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlistmiaosha=new ArrayList<>();
        listmiaosha.put("operation", "getmiaosha");
        listmiaosha.put("bookName", priceSelectorUserInput.getText().toString());
        Log.i("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listmiaosha);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall", datelistTmall.toString());
            Log.i("array", array.toString());

            int size = array.length();
            //System.out.println("size=" + size);

            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShow.class);

            if(size>0) {
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("bookName"));
                    hashmap.put("price", (String) object.get("bookPrice"));
                    hashmap.put("commentNum", (String) object.get("commentNum"));
                    hashmap.put("commentValue", (String) object.get("commentValue"));
                    simpleadapterlistmiaosha.add(hashmap);

                }
                handler3.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                //Intent intent = new Intent();
                //intent.setClass(getActivity(), PriceSelectorShow.class);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlistmiaosha);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler3.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selectjinpai() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlistjinpai=new ArrayList<>();
        listjinpai.put("operation", "getjinpai");
        listjinpai.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listjinpai);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall", datelistTmall.toString());

            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShow.class);

            int size = array.length();
            System.out.println("size=" + size);
            if(size>0) {
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("Tmall_Name"));
                    hashmap.put("price", (String) object.get("Tmall_Price"));
                    hashmap.put("commentNum", (String) object.get("Tmall_commentNum"));
                    hashmap.put("commentValue", (String) object.get("Tmall_commentValue"));
                    simpleadapterlistjinpai.add(hashmap);

                }
                handler4.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlistjinpai);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        } else {
            handler4.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selectjingdongjiage() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlisttuijianJDjiage=new ArrayList<>();
        listjingdongjiage.put("operation", "getjingdongjiage");
        listjingdongjiage.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listjingdongjiage);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall", datelistTmall.toString());

            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShowTuiJian.class);
            int size = array.length();
            if(size > 0 ){
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("bookName"));
                    hashmap.put("price", (String) object.get("bookPrice"));
                    hashmap.put("commentNum", (String) object.get("commentNum"));
                    hashmap.put("commentValue", (String) object.get("commentValue"));
                    hashmap.put("tuijiandu", (String) object.get("tuijian_JD_jiage"));
                    simpleadapterlisttuijianJDjiage.add(hashmap);
                }
                handler5.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlisttuijianJDjiage);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler5.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selectjingdongzhiliang() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlisttuijianJDzhiliang=new ArrayList<>();
        listjingdongzhiliang.put("operation", "getjingdongzhiliang");
        listjingdongzhiliang.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listjingdongzhiliang);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall",datelistTmall.toString());

            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShowTuiJian.class);
            int size = array.length();
            if(size > 0){
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("bookName"));
                    hashmap.put("price", (String) object.get("bookPrice"));
                    hashmap.put("commentNum", (String) object.get("commentNum"));
                    hashmap.put("commentValue", (String) object.get("commentValue"));
                    hashmap.put("tuijiandu", (String) object.get("tuijian_JD_zhiliang"));
                    simpleadapterlisttuijianJDzhiliang.add(hashmap);

                }
                handler6.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlisttuijianJDzhiliang);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler6.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selecttaobaojiage() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlisttuijianTBjiage=new ArrayList<>();
        listtaobaojiage.put("operation", "gettaobaojiage");
        listtaobaojiage.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listtaobaojiage);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall",datelistTmall.toString());


            int size = array.length();
            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShowTuiJian.class);
            if(size > 0){
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("Tmall_Name"));
                    hashmap.put("price", (String) object.get("Tmall_Price"));
                    hashmap.put("commentNum", (String) object.get("Tmall_commentNum"));
                    hashmap.put("commentValue", (String) object.get("Tmall_commentValue"));
                    hashmap.put("tuijiandu", (String) object.get("tuijian_TB_jiage"));
                    simpleadapterlisttuijianTBjiage.add(hashmap);

                }
                handler7.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlisttuijianTBjiage);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler7.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

    private void selecttaobaozhiliang() throws IOException, JSONException {
        List<HashMap<String, Object>> simpleadapterlisttuijianTBzhiliang=new ArrayList<>();
        listtaobaozhiliang.put("operation", "gettaobaozhiliang");
        listtaobaozhiliang.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listtaobaozhiliang);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall",datelistTmall.toString());


            int size = array.length();
            Intent intent = new Intent();
            intent.setClass(getActivity(), PriceSelectorShowTuiJian.class);
            if(size > 0){
                for (int i = 0; i < size; i++) {
                    JSONObject object = array.getJSONObject(i);
                    HashMap<String, Object> hashmap = new HashMap<String, Object>();
                    hashmap.put("name", (String) object.get("Tmall_Name"));
                    hashmap.put("price", (String) object.get("Tmall_Price"));
                    hashmap.put("commentNum", (String) object.get("Tmall_commentNum"));
                    hashmap.put("commentValue", (String) object.get("Tmall_commentValue"));
                    hashmap.put("tuijiandu", (String) object.get("tuijian_TB_zhiliang"));
                    simpleadapterlisttuijianTBzhiliang.add(hashmap);

                }
                handler8.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
                Bundle bundle = new Bundle();
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(simpleadapterlisttuijianTBzhiliang);
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Bundle bundle = new Bundle();
                ArrayList bundlelist = null;
                bundle.putParcelableArrayList("jingdong", bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            handler8.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }
}
