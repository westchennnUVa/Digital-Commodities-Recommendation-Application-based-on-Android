package com.example.westchen.priceselector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.model.Product;

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

/**
 * Created by Westchennn on 17/3/17.
 */
public class PriceSelector extends Activity {

    private Button priceSelectorConfirmButton;
    private Button priceSelectorConfirmButtonTmall;
    private EditText priceSelectorUserInput;

    private JSONArray array;

    // 产品列表
    private ArrayList<Product> proList;

    private ArrayList bundlelist;




    private Handler handler;
    private Handler handler2;

    public static final int HANDLER_LOGIN_SUCCESS=1;
    public static final int HANDLER_LOGIN_ERROR=2;

    //Map<String,String> list=new HashMap<String,String>();

    Map<String,String> list=new LinkedHashMap<String,String>();
    Map<String,String> listTmall=new LinkedHashMap<String,String>();


    //private SimpleAdapter simple;
    private List<HashMap<String, Object>> simpleadapterlistTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priceselector);
        setViews();
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
                        Toast.makeText(PriceSelector.this, "京东查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(PriceSelector.this, "京东查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(PriceSelector.this, "天猫查价成功", Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(PriceSelector.this, "天猫查价失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

//        adapter = new MyListAdapter(PriceSelector.this);
//        adapter.setListItems(proList);
//        jingDongList.setAdapter(adapter);

//        simple=new SimpleAdapter(this, simpleadapterlistTest,
//                R.layout.jingdonglistview, new String[]{"name","price"}, new int[]{R.id.item_name,R.id.item_price});
//        jingDongList.setAdapter(simple);


//        ArrayAdapter<Product> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_2,proList);
//        jingDongList.setAdapter(arrayAdapter);


    }


    private void setViews(){
        priceSelectorConfirmButton = (Button)findViewById(R.id.userInputConfirmButton);
        priceSelectorConfirmButtonTmall = (Button)findViewById(R.id.userInputConfirmButtonTmall);
        priceSelectorUserInput = (EditText)findViewById(R.id.userInput);
        //jingDongList = (ListView)findViewById(R.id.jingDongProductList);
        // 初始化查询产品列表
        proList = new ArrayList<Product>();
        simpleadapterlistTest = new ArrayList<HashMap<String, Object>>();
    }

    private /*List<String>*/void usePriceSelector() throws IOException, JSONException{
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
            for(int i=0;i<size;i++) {
                //bookID[i] = array.get(i).toString();
                JSONObject object = array.getJSONObject(i);
//                Product tempPro = new Product();
                HashMap<String, Object> hashmap = new HashMap<String, Object>();
//                tempPro.setBookID((String)object.get("bookID"));
//                tempPro.setBookPrice((String) object.get("bookPrice"));
//                tempPro.setBookName((String) object.get("bookName"));
                hashmap.put("name", (String) object.get("bookName"));
                hashmap.put("price", (String) object.get("bookPrice"));
                simpleadapterlistTest.add(hashmap);



//                proList.add(tempPro);
            }
//            Log.i("testtest", proList.get(2).getBookPrice());

            handler.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
            Intent intent = new Intent();
            intent.setClass(PriceSelector.this,PriceSelectorShow.class);
            Bundle bundle = new Bundle();
            ArrayList bundlelist = new ArrayList();
            bundlelist.add(simpleadapterlistTest);
            bundle.putParcelableArrayList("jingdong",bundlelist);
            intent.putExtras(bundle);
            startActivity(intent);

        }
        else {
            handler.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }


    }

    private void usePriceSelectorTmall() throws IOException, JSONException {
        listTmall.put("operation", "getTmallPrice");
        listTmall.put("bookName", priceSelectorUserInput.getText().toString());
        String resultTmall = new HttpUtil().post(CommonUrl.PriceSelector, listTmall);
        if (!resultTmall.equals("-1")) {
            JSONArray datelistTmall = new JSONArray(resultTmall);
            array = datelistTmall;
            Log.i("tmall",datelistTmall.toString());


            int size = array.length();
            for (int i = 0; i < size; i++) {
                JSONObject object = array.getJSONObject(i);
                HashMap<String, Object> hashmap = new HashMap<String, Object>();
                hashmap.put("name", (String) object.get("Tmall_Name"));
                hashmap.put("price", (String) object.get("Tmall_Price"));
                simpleadapterlistTest.add(hashmap);

            }
            handler2.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
            Intent intent = new Intent();
            intent.setClass(PriceSelector.this, PriceSelectorShow.class);
            Bundle bundle = new Bundle();
            ArrayList bundlelist = new ArrayList();
            bundlelist.add(simpleadapterlistTest);
            bundle.putParcelableArrayList("jingdong", bundlelist);
            intent.putExtras(bundle);
            startActivity(intent);

        } else {
            handler.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }

}
