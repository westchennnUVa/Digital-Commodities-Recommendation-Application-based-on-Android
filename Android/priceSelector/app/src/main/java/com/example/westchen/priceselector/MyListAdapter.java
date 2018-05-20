package com.example.westchen.priceselector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.model.Product;

import java.util.List;

/**
 * Created by Westchennn on 17/3/27.
 */
public class MyListAdapter extends BaseAdapter {
    private List<Product> listItems;
    private LayoutInflater layoutInflater;

    public MyListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public List<Product> getListItems() {
        return listItems;
    }

    public void setListItems(List<Product> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        ListItemView myListItemView;
        if (convertView == null) {
            myListItemView = new ListItemView();
            convertView = layoutInflater
                    .inflate(R.layout.jingdonglistview, null);
            myListItemView.setproduct_Name((TextView) convertView
                    .findViewById(R.id.item_name));
            myListItemView.setproduct_Price((TextView) convertView
                    .findViewById(R.id.item_price));
            convertView.setTag(myListItemView);
        } else {
            myListItemView = (ListItemView) convertView.getTag();
        }
        Log.i("adaptertest",listItems.get(1).getBookPrice());
        myListItemView.getproduct_Name()
                .setText(listItems.get(position).getBookName());
        myListItemView.getproduct_Price().setText(listItems.get(position).getBookPrice());
//        myListItemView.getIv_image().setImageResource(
//                listItems.get(position).getRid());

        return convertView;
    }

    class ListItemView {
        private TextView product_Name;
        private TextView product_Price;

        public TextView getproduct_Name() {
            return product_Name;
        }

        public void setproduct_Name(TextView product_Name) {
            this.product_Name = product_Name;
        }

        public TextView getproduct_Price() {
            return product_Price;
        }

        public void setproduct_Price(TextView product_Price) {
            this.product_Price = product_Price;
        }

    }
}
