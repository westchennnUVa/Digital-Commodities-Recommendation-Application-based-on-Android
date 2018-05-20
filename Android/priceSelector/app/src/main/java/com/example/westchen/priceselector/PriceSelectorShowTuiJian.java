package com.example.westchen.priceselector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Westchennn on 17/4/19.
 */
public class PriceSelectorShowTuiJian extends Activity {
    private List<HashMap<String, Object>> adapterShowList;
    private ListView jingDongList;
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priceselector_show);
        jingDongList = (ListView)findViewById(R.id.jingDongProductList);
        adapterShowList = new ArrayList<HashMap<String, Object>>();
        Bundle bundle = getIntent().getExtras();
        ArrayList list = bundle.getParcelableArrayList("jingdong");
        adapterShowList = (List<HashMap<String, Object>>)list.get(0);
        adapterShowList = (List<HashMap<String, Object>>) list.get(0);
        for(int c=0;c<adapterShowList.size();c++){
            ((HashMap)adapterShowList.get(c)).put("price", ("价格："+ (String)adapterShowList.get(c).get("price")));
            ((HashMap)adapterShowList.get(c)).put("commentNum", ("评论量："+ (String)adapterShowList.get(c).get("commentNum")));
            ((HashMap)adapterShowList.get(c)).put("commentValue", ("评分值："+ (String)adapterShowList.get(c).get("commentValue")));
            ((HashMap)adapterShowList.get(c)).put("tuijiandu", ("推荐度："+ (String)adapterShowList.get(c).get("tuijiandu")));
        }
        Log.i("simpletest", adapterShowList.toString());
        SimpleAdapter simAdapter = new SimpleAdapter(
                PriceSelectorShowTuiJian.this, adapterShowList, R.layout.tuijianlistview,
                new String[] { "name", "price","commentNum","commentValue","tuijiandu" }, new int[] {
                R.id.item_name, R.id.item_price,R.id.item_commentNum,R.id.item_commentValue,R.id.item_tuijian});
        jingDongList.setAdapter(simAdapter);


    }
}
