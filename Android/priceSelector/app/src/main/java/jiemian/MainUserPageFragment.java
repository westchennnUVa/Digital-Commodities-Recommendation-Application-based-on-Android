package jiemian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.westchen.priceselector.R;



public class MainUserPageFragment extends Fragment {

    private ImageView item1;
    private ImageView item2;
    private ImageView item3;
    private ImageView item4;
    private ImageView item5;
    private ImageView item6;
    private ImageView item7;
    private ImageView item8;
    private ImageView item9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main_user_page, null);

//        item1=(ImageView)view.findViewById(R.id.item1);
//        item2=(ImageView)view.findViewById(R.id.item2);
//        item3=(ImageView)view.findViewById(R.id.item3);
//        item4=(ImageView)view.findViewById(R.id.item4);
//        item5=(ImageView)view.findViewById(R.id.item5);
//        item6=(ImageView)view.findViewById(R.id.item6);
//        item7=(ImageView)view.findViewById(R.id.item7);
//        item8=(ImageView)view.findViewById(R.id.item8);
//        item9=(ImageView)view.findViewById(R.id.item9);
//
//        item1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getActivity(), DrugItem1.class);
//                startActivity(intent1);
//            }
//        });
//
//        item2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(getActivity(), SportItem2.class);
//                startActivity(intent2);
//            }
//        });
//
//        item3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(getActivity(), MentalityItem1.class);
//                startActivity(intent3);
//            }
//        });
//
//        item4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent4 = new Intent(getActivity(),MessageContent.class);
//                startActivity(intent4);
//            }
//        });
//
//        item5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent5 = new Intent(getActivity(), RegimenItem1.class);
//                startActivity(intent5);
//            }
//        });
//        item6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent6 = new Intent(getActivity(), RegimenItem2.class);
//                startActivity(intent6);
//            }
//        });
//        item7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent7 = new Intent(getActivity(), DrugItem2.class);
//                startActivity(intent7);
//            }
//        });
//        item8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent8 = new Intent(getActivity(), SportItem1.class);
//                startActivity(intent8);
//            }
//        });
//        item9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent9 = new Intent(getActivity(), RegimenItem3.class);
//                startActivity(intent9);
//            }
//        });
        return view;
    }

}
