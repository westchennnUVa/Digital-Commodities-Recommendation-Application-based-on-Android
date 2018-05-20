package com.example.westchen.priceselector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jiemian.BaseFragment;
import url.CommonUrl;
import util.HttpUtil;

/**
 * Created by Westchennn on 17/3/16.
 */
public class LoginActivity extends Activity {
    private EditText username;
    private EditText password;
    private CheckBox remember;
    private ImageView ret;
    private Button login;
    private JSONObject object;
    private String ruserName;
    private String rpassword;
    private String rage;
    private String rname;
    private String rrealname;
    private String rsgender;
    private String rIDnum;
    private String rTelnum;
    private String rstouxiang;

    private String userid;
    private String doctorid;
    private String hospital;
    private String section;

    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    Map<String,String> list=new HashMap<String,String>();

    private Handler handler;

    public static final int HANDLER_LOGIN_SUCCESS=1;
    public static final int HANDLER_LOGIN_ERROR=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        editor = sp.edit();
        setViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            login();
                            if (remember.isChecked()) {
                                //记住用户名、密码、
                                editor.putString("USER_NAME", ruserName);
                                editor.putString("PASSWORD", rpassword);
                                //editor.commit();
                            }
                            remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (remember.isChecked()) {

                                        System.out.println("记住密码已选中");
                                        sp.edit().putBoolean("ISCHECK", true).commit();
                                    } else {
                                        System.out.println("记住密码没有选中");
                                        sp.edit().putBoolean("ISCHECK", false).commit();
                                    }
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error", "登录失败");
                        }
                    }
                }.start();
            }
        });

        handler=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_LOGIN_SUCCESS:
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, BaseFragment.class);
                        i.putExtra("realName",rrealname);
                        i.putExtra("age",rage);
                        i.putExtra("id", userid);
                        i.putExtra("sex",rsgender);
                        startActivity(i);
                        break;
                    case HANDLER_LOGIN_ERROR:
                        Toast.makeText(LoginActivity.this, "登录失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    private void setViews() {
        username = (EditText)findViewById(R.id.usernameid);
        password = (EditText)findViewById(R.id.passwordid);
        remember = (CheckBox)findViewById(R.id.gender);
        login = (Button) findViewById(R.id.login);

        if(sp.getBoolean("ISCHECK", true))
        {
            //设置默认是记录密码状态
            remember.setChecked(true);
            username.setText(sp.getString("USER_NAME", username.getText().toString()));
            password.setText(sp.getString("PASSWORD", password.getText().toString()));
        }
    }
    private void login() throws IOException, JSONException {
        list.put("operation", "Login");
        list.put("userName", username.getText().toString());
        list.put("password", password.getText().toString());
        Log.i("test", password.getText().toString());

        String result = new HttpUtil().post(CommonUrl.Login,list );
        Log.i("我是result",result);
        if(!result.equals("-1"))
        {

            Log.i("get data", result.toString());
            JSONObject datelist = new JSONObject(result);

            //将返回信息解析
            object = datelist;
            userid = (String)object.get("userID");
            ruserName = (String)object.get("userName");
            rpassword = (String)object.get("password");
            rage = (String)object.get("age");
            rname = (String)object.get("name");
            rrealname = (String)object.get("realName");
            rsgender = (String)object.get("sex");
            rIDnum = (String)object.get("personalID");
            rTelnum = (String)object.get("phone");
            rstouxiang = (String)object.get("head");

            //setData();
            editor.putString("userID", userid);
            editor.putString("userName", ruserName);
            editor.putString("password", rpassword);
            editor.putString("age", rage);
            editor.putString("name", rname);
            editor.putString("realName", rrealname);
            editor.putString("sex", rsgender);
            editor.putString("personalID", rIDnum);
            editor.putString("phone", rTelnum);
            editor.putString("head", rstouxiang);
            editor.commit();


            handler.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
        }
        else
        {
            handler.sendEmptyMessage(HANDLER_LOGIN_ERROR);
        }
    }
}
