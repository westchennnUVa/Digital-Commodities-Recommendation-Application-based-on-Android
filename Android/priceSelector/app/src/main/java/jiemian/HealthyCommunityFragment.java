package jiemian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.westchen.priceselector.R;

public class HealthyCommunityFragment extends Fragment {

    private String id,realName,age,sex;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_community, null);

//        iv1 = (ImageView)view.findViewById(R.id.imageView1);
//        iv2 = (ImageView)view.findViewById(R.id.imageView2);
//        iv3 = (ImageView)view.findViewById(R.id.imageViewmap);
//        iv4 = (ImageView)view.findViewById(R.id.imageView4);
//        iv5 = (ImageView)view.findViewById(R.id.imageView5);
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        age = bundle.getString("age");
        realName = bundle.getString("realName");
        sex = bundle.getString("sex");


//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), HealthNewsActivity.class);
//                startActivity(intent);
//            }
//        });
//        iv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /*Intent intent = new Intent(getActivity(), PersonalActivityFragment.class);
//                startActivity(intent);*/
//            }
//        });
//        iv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), map_MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        iv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
//                intent.putExtra("id",id);
//                startActivity(intent);
//            }
//        });
//        iv5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), TreatmentActivity.class);
//                intent.putExtra("id",id);
//                intent.putExtra("age",age);
//                intent.putExtra("realName",realName);
//                intent.putExtra("sex",sex);
//                startActivity(intent);
//            }
//        });
        return view;
    }

}

