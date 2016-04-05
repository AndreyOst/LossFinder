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

public class TypeAdapter extends BaseAdapter {

    private Context context;
    private String[] type = {"Loss", "Found"};
    private int[] image = {R.mipmap.ic_loss, R.mipmap.ic_found};

    public TypeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return type.length;
    }

    @Override
    public Object getItem(int position) {
        return type[position];
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
            view = inflater.inflate(R.layout.fragment_type_item, parent, false);
        } else {
            view = convertView;
        }
        ImageView ivType = (ImageView) view.findViewById(R.id.ivTypeImageItem);
        TextView tvType = (TextView) view.findViewById(R.id.tvTypeNameItem);
        ivType.setImageResource(image[position]);
        tvType.setText(type[position]);

        return view;
    }
}
