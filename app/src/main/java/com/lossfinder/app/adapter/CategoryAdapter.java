package com.lossfinder.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.lossfinder.app.R;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private String[] category = {"Document", "Keys", "Animals",
            "Bag", "Clothes", "Telephone", "Electronica", "Transport", "Jewerly"
            , "Others"};
    private int[] image = {R.mipmap.ic_document, R.mipmap.ic_keys, R.mipmap.ic_animals,
            R.mipmap.ic_bag, R.mipmap.ic_clothes, R.mipmap.ic_telephone,
            R.mipmap.ic_electronica, R.mipmap.ic_transport, R.mipmap.ic_jewerly, R.mipmap.ic_others};

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return category.length;
    }

    @Override
    public Object getItem(int position) {
        return category[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_category_item, parent, false);
        } else {
            view = convertView;
        }

        ImageView ivCategory = (ImageView) view.findViewById(R.id.ivCategoryImageItem);
        TextView tvCategory = (TextView) view.findViewById(R.id.tvCategoryNameItem);
        ivCategory.setImageResource(image[position]);
        tvCategory.setText(category[position]);

        return view;
    }
}
