package com.example.westchen.priceselector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Westchennn on 17/3/16.
 */
public class MainPage extends Activity{
    private Button denglu;
    private Button zhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        denglu = (Button)findViewById(R.id.denglu);
        zhuce = (Button)findViewById(R.id.zhuce);

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainPage.this,LoginActivity.class);
                startActivity(intent1);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainPage.this,RegisterActivity.class);
                startActivity(intent2);
            }
        });
    }
}
