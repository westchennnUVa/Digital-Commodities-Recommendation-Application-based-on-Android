package jiemian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.westchen.priceselector.R;



public class MyDataFragment extends Fragment{

    private TextView userName;
    private TextView agetv;
    private TextView sextv;
    private String id,realName,age,sex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_my_data, null);

        userName = (TextView)view.findViewById(R.id.userName);
        agetv=(TextView)view.findViewById(R.id.age);
        sextv=(TextView)view.findViewById(R.id.sex);

        Bundle bundle = getArguments();
        id = bundle.getString("id");
        age = bundle.getString("age");
        realName = bundle.getString("realName");
        sex = bundle.getString("sex");
        //userName.setText("haohaochen");

        System.out.println("用户id"+id);
        System.out.println("用户age"+age);
        System.out.println("用户sex"+sex);
        System.out.println("用户name"+realName);

        userName.setText(realName);
        agetv.setText(age);
        sextv.setText(sex);
        return view;
    }

}


