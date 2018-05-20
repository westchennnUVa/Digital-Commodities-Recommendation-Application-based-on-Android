package com.example.westchen.priceselector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;
import util.Tools;

/**
 * Created by Westchennn on 17/3/16.
 */
public class RegisterActivity extends Activity {
    private EditText username;
    private EditText password;
    private EditText age;
    private EditText name;
    private EditText realname;
    private RadioButton gender;
    private EditText IDnum;
    private EditText Telnum;
    private Button zhucewancheng;
    private ImageView touxiang;
    private Button wancheng;
    private String stouxiang;
    private String sgender;
    private String type = "普通用户";
    private Bitmap photo;
    Map<String, String> list = new HashMap<String, String>();

    private String[] items = new String[]{"选择本地图片", "拍照"};
    /*头像名称*/
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";

    /* 请求码*/
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_REGISTER_SUCCESS:
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    handler.sendEmptyMessage(HANDLER_REGISTER_SUCCESS);
                    startActivity(intent);
                    finish();
                    break;

                case HANDLER_REGISTER_ERROR:
                    Toast.makeText(RegisterActivity.this, "注册失败" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public static final int HANDLER_REGISTER_SUCCESS = 1;
    public static final int HANDLER_REGISTER_ERROR = 2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setViews();
    }

    public void setViews() {
        username = (EditText) findViewById(R.id.usernameet);
        password = (EditText) findViewById(R.id.passwordet);
        age = (EditText) findViewById(R.id.ageet);
        name = (EditText) findViewById(R.id.nameet);
        realname = (EditText) findViewById(R.id.realnameet);
        RadioGroup gendergp = (RadioGroup) findViewById(R.id.genderrg);
        gender = (RadioButton) findViewById(gendergp.getCheckedRadioButtonId());

        if (gendergp.getId() == R.id.male) {
            sgender = "male";
        } else {
            sgender = "female";
        }

        IDnum = (EditText) findViewById(R.id.Idnum);
        Telnum = (EditText) findViewById(R.id.Telnum);
        wancheng = (Button) findViewById(R.id.wancheng);
        //touxiang = (ImageView) findViewById(R.id.touxiang);
        zhucewancheng = (Button) findViewById(R.id.wancheng);


        /*
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                touxiang.setImageBitmap(photo);
            }
        });

        touxiang.setDrawingCacheEnabled(true);
        Bitmap btouxiang = touxiang.getDrawingCache();
        */






        //    stouxiang = new BitmapUtils().bitmaptoString(btouxiang);

    /*    touxiang.setDrawingCacheEnabled(true);
        Drawable d = touxiang.getDrawable();
        Bitmap btouxiang = BitmapFactory.decodeResource(this.getResources(),d);
        stouxiang = new BitmapUtils().bitmaptoString(btouxiang);*/

        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            zhuce();
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
    }

    /**
     * 显示选择对话框
     */
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("设置头像")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intentFromGallery = new Intent();
                                intentFromGallery.setType("image/*"); // 设置文件类型
                                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                                break;
                            case 1:
                                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 判断存储卡是否可以用，可用进行存储
                                if (Tools.hasSdcard()) {
                                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                                }
                                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case IMAGE_REQUEST_CODE:
//                startPhotoZoom(data.getData());
//                break;
//            case CAMERA_REQUEST_CODE:
//                if (Tools.hasSdcard()) {
//                    File tempFile = new File(Environment.getExternalStorageDirectory() + IMAGE_FILE_NAME);
//                    startPhotoZoom(Uri.fromFile(tempFile));
//                } else {
//                    Toast.makeText(RegisterActivity.this, "未找到存储卡，无法存储照片！",
//                            Toast.LENGTH_LONG).show();
//                }
//                break;
//            case RESULT_REQUEST_CODE:
//                if (data != null) {
//                    getImageToView(data);
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    //裁剪图片方法实现
//    public void startPhotoZoom(Uri uri) {
//
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 320);
//        intent.putExtra("outputY", 320);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 2);
//    }
//
//    //保存裁剪之后的图片数据
//    private void getImageToView(Intent data) {
//        Bundle extras = data.getExtras();
//        if (extras != null) {
//            photo = extras.getParcelable("data");
//            Drawable drawable = new BitmapDrawable(photo);
//            touxiang.setImageDrawable(drawable);
//            //将extra传递给数据库
//        }
//    }

    private void zhuce() throws IOException, JSONException {
        list.put("operation", "Enroll_BaseUser");
        list.put("userName", username.getText().toString());
        list.put("password", password.getText().toString());
        list.put("age", age.getText().toString());
        list.put("name", name.getText().toString());
        list.put("realName", realname.getText().toString());
        list.put("sex", sgender);
        list.put("personalID", IDnum.getText().toString());
        list.put("phone", Telnum.getText().toString());
        list.put("head", stouxiang);
        list.put("type", type);

        Log.i("test", username.getText().toString());


        //以下的url需要改 而且服务器和客户端之间也还没连
        String result = new HttpUtil().post(CommonUrl.Login, list);
        if (!result.equals("-1")) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_LONG);
        }
    }
}
