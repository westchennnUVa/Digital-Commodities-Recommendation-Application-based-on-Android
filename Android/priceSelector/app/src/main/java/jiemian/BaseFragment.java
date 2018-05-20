package jiemian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.westchen.priceselector.R;

import java.util.ArrayList;

public class BaseFragment extends FragmentActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton r0;
    private RadioButton r1;
    private MyPagerAdapter adapter;
    private ArrayList<Fragment> fs;
    //private FragmentInterface mFragment;
    private String id, realName, age, sex;

    private android.app.FragmentManager manager;
    private android.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        setViews();
        setListener();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        realName = intent.getStringExtra("realName");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        PersonalActivityFragment fragment1 = new PersonalActivityFragment();
        MyDataFragment fragment2 = new MyDataFragment();

        Bundle bundle1 = new Bundle();
        bundle1.putString("id", id);
        bundle1.putString("age", age);
        bundle1.putString("realName", realName);
        bundle1.putString("sex", sex);

        fragment1.setArguments(bundle1);
        fragment2.setArguments(bundle1);

        transaction.commit();

        fs = new ArrayList<Fragment>();
        fs.add(fragment1);
        fs.add(fragment2);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void setListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        r0.setChecked(true);
                        break;
                    case 1:
                        r1.setChecked(true);
                        break;
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.radio0:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio1:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    private void setViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup1);
        r0 = (RadioButton) findViewById(R.id.radio0);
        r1 = (RadioButton) findViewById(R.id.radio1);

    }



    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return fs.get(position);
        }

        public int getCount() {
            return fs.size();
        }

    }
}

