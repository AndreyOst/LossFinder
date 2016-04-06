package com.lossfinder.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lossfinder.app.R;

public class PostFragment extends Fragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(FRAGMENT_LAYOUT, container, false);
    }
}
