package com.lossfinder.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.lossfinder.app.R;
import com.lossfinder.app.adapter.TypeAdapter;

public class TypeFragment extends Fragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_type;
    private static final int LIST_TYPE = R.id.lvType;

    private ListView lvType;

    private String[] type = {"Loss", "Found"};
    private int[] image = {R.mipmap.ic_loss, R.mipmap.ic_found};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(FRAGMENT_LAYOUT, container, false);

        lvType = (ListView) view.findViewById(LIST_TYPE);
        lvType.setAdapter(new TypeAdapter(this.getContext()));

        return view;
    }
}
