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
import com.lossfinder.app.constant.Constant;
import com.lossfinder.app.adapter.CategoryAdapter;
import com.lossfinder.app.listener.OnCategorySelectedListener;

public class CategoryFragment extends Fragment {

    private OnCategorySelectedListener categorySelectedListener;

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
        View view = inflater.inflate(Constant.CATEGORY_FRAGMENT_LAYOUT, container, false);

        ListView lvCategory = (ListView) view.findViewById(Constant.CATEGORY_FRAGMENT_LIST);
        lvCategory.setAdapter(new CategoryAdapter(this.getContext()));

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedListener.onCategorySelected();
            }
        });

        return view;
    }
}





