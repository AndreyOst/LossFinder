package com.lossfinder.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.lossfinder.app.R;
import com.lossfinder.app.adapter.CategoryAdapter;
import com.lossfinder.app.interfaces.OnCategorySelectedListener;

public class CategoryFragment extends Fragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_category;
    private static final int LIST_CATEGORY = R.id.lvCategory;

    private OnCategorySelectedListener categorySelectedListener;
    private ListView lvCategory;

    private String[] category = {"Document", "Keys", "Animals",
            "Bag", "Clothes", "Telephone", "Electronica", "Transport", "Jewerly", "Others"};

    private int[] image = {R.mipmap.ic_document, R.mipmap.ic_keys, R.mipmap.ic_animals,
            R.mipmap.ic_bag, R.mipmap.ic_clothes, R.mipmap.ic_telephone,
            R.mipmap.ic_electronica, R.mipmap.ic_transport, R.mipmap.ic_jewerly, R.mipmap.ic_others};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            categorySelectedListener = (OnCategorySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCategorySelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(FRAGMENT_LAYOUT, container, false);

        lvCategory = (ListView) view.findViewById(LIST_CATEGORY);
        lvCategory.setAdapter(new CategoryAdapter(this.getContext()));
        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedListener.OnCategorySelected();
            }
        });

        return view;
    }
}





