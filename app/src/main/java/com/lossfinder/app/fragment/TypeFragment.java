package com.lossfinder.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.lossfinder.app.Constant;
import com.lossfinder.app.adapter.TypeAdapter;

public class TypeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(Constant.TYPE_FRAGMENT_LAYOUT, container, false);

        ListView lvType = (ListView) view.findViewById(Constant.TYPE_FRAGMENT_LIST);
        lvType.setAdapter(new TypeAdapter(this.getContext()));

        return view;
    }
}
